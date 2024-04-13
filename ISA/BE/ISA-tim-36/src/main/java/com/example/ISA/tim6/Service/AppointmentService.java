package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Dtos.AppointmentSaveDto;
import com.example.ISA.tim6.Dtos.SaveReportDto;
import com.example.ISA.tim6.Model.Appointment;
import com.example.ISA.tim6.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("appointmentService")
public class AppointmentService {

    @Autowired
    private EmailSenderService senderService;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final CentreRepository centreRepository;
    private final EquipmentRepository equipmentRepository;

    private final BloodTypesRepository bloodTypesRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              UserRepository userRepository,
                              CentreRepository centreRepository,
                              EquipmentRepository equipmentRepository,
                              BloodTypesRepository bloodTypesRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.centreRepository = centreRepository;
        this.equipmentRepository = equipmentRepository;
        this.bloodTypesRepository = bloodTypesRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByCentre(Long centreId) {
        return appointmentRepository.findByCentreId(centreId);
    }

    public ResponseEntity<Appointment> saveAppointment(AppointmentSaveDto requestBody) {
        var userOpt = userRepository.findById(requestBody.getMedicalWorker());
        var centreOpt = centreRepository.findById(requestBody.getCentre());
        if (userOpt.isPresent() && centreOpt.isPresent()) {
            var medicalWorker = userOpt.get();
            var centre = centreOpt.get();
            var appointment = new Appointment();

            Optional<Appointment> existingAppointment = appointmentRepository.findByDateTimeBetweenAndMedicalWorker(requestBody.getDateAndTime(),
                    requestBody.getDateAndTime().plusMinutes(requestBody.getDuration()),
                    medicalWorker);

            if (existingAppointment.isPresent()) {
                return ResponseEntity.badRequest().build();
            }

            appointment.setDateTime(requestBody.getDateAndTime());
            appointment.setDurationInMinutes(requestBody.getDuration());
            appointment.setMedicalWorker(medicalWorker);
            appointment.setCentre(centre);
            Appointment save = appointmentRepository.save(appointment);
            return ResponseEntity.ok(save);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Appointment> getAppointmentById(Long id) {
        var optional = appointmentRepository.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> saveReport(SaveReportDto requestBody) {
        var appointmentOptional = appointmentRepository.findById(requestBody.id());
        if (appointmentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointment = appointmentOptional.get();
        var user = appointment.getUser();

        if (requestBody.didNotShow()) {
            user.setPoints(user.getPoints() + 1);
        }

        appointment.setConditionsNotFulfilled(requestBody.conditionsNotFulfilled());
        appointment.setDidNotShow(requestBody.didNotShow());
        appointment.setInfo(requestBody.details());

        appointmentRepository.save(appointment);

        requestBody.spentEquipment().forEach(eq -> {
            var equipmentOpt = equipmentRepository.findById(eq.id());
            equipmentOpt.ifPresent(equipment -> equipment.setStockQuantity(Math.max(equipment.getStockQuantity() - eq.selectedQuantity(), 0)));
            equipmentRepository.save(equipmentOpt.get());
        });

        requestBody.spentBloodTypes().forEach(bt -> {
            var bloodTypeOpt = bloodTypesRepository.findById(bt.id());
            bloodTypeOpt.ifPresent(bloodTypes -> bloodTypes.setStockQuantity(Math.max(bloodTypes.getStockQuantity() - bt.selectedQuantity(), 0)));
            bloodTypesRepository.save(bloodTypeOpt.get());
        });

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> registerUserToAppointment(long appointmentId, long userId)
        {
            Optional<Appointment> updatedAppointment;
            updatedAppointment = appointmentRepository.findById(appointmentId);
            if(updatedAppointment!=null)
            {
                try {
                    updatedAppointment.get().setUser(userRepository.findById(userId).get());
                    Appointment save = appointmentRepository.save(updatedAppointment.get());
                    senderService.sendEmail("fkcrvenka1919@gmail.com","Uspesno zakazivanje","Uspesno ste zakazali termin. Datum: " + updatedAppointment.get().getDateTime()+" trajanje: " + updatedAppointment.get().getDurationInMinutes()+"min");

                    return ResponseEntity.ok(save);
                }
                catch (Exception e)
                {

                    return ResponseEntity.internalServerError().build();
                }
            }
            else
            {
                return ResponseEntity.badRequest().build();
            }
    }
}
