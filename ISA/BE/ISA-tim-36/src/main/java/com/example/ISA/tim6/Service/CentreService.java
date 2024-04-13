package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Dtos.AppointmentSearchDTO;
import com.example.ISA.tim6.Dtos.CentreSaveDto;
import com.example.ISA.tim6.Model.Appointment;
import com.example.ISA.tim6.Model.Centre;
import com.example.ISA.tim6.Model.User;
import com.example.ISA.tim6.Repository.AppointmentRepository;
import com.example.ISA.tim6.Repository.CentreRepository;
import com.example.ISA.tim6.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CentreService {

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Centre>> getAllCentres() {
        try
        {
            List<Centre> centres = new ArrayList<Centre>();
            centreRepository.findAll().forEach(centres::add);
            if (centres.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(centres, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Centre> getCentreById(Long id) {
        Optional<Centre> centreData = centreRepository.findById(id);

        if (centreData.isPresent()) {
            return new ResponseEntity<>(centreData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Centre> addNewCentre(CentreSaveDto dto) {
        try {
            Optional<User> admin = userRepository.findById(dto.getAdminId());
            if (admin.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            List<User> admins = new ArrayList<>();
            admins.add(admin.get());
            Centre centre = new Centre(dto.getName(), dto.getAddress(), dto.getNumber(), dto.getDescription(), dto.getAverageRate(),admins);
            Centre _centre = centreRepository.save(centre);
            return new ResponseEntity<>(_centre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Centre> updateCentre(long id, Centre centre){

        Optional<Centre> centreData = centreRepository.findById(id);

        if (centreData.isPresent()) {
            Centre _centre = centreData.get();


            _centre.setName(centre.getName());
            _centre.setStreetName(centre.getStreetName());
            _centre.setStreetNumber(centre.getStreetNumber());
            _centre.setDescription(centre.getDescription());
            _centre.setAverageRate(centre.getAverageRate());
            _centre.setAdmins(centre.getAdmins());

            return new ResponseEntity<>(centreRepository.save(_centre), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Centre>> searchCentersByAppointmentTime(AppointmentSearchDTO timeOfAppointment)
    {
        LocalDateTime dateTime = timeOfAppointment.dateTime;
        try
        {
            System.out.println("DOSAO DO OVDE1!");
            //Find appointments
            List<Appointment> availableAppointments = new ArrayList<Appointment>();
            List<Appointment> allAppointments = new ArrayList<Appointment>();

            System.out.println("DOSAO DO OVDE11!");
            appointmentRepository.findAll().forEach(allAppointments::add);

            System.out.println("DOSAO DO OVDE12!");
            for (Appointment appointment:allAppointments)
            {
                if(appointment.isAvailableInThatTime(dateTime) && appointment.getUser()==null)
                {
                    availableAppointments.add(appointment);
                }
            }
            System.out.println("DOSAO DO OVDE2!");
            if (availableAppointments.isEmpty())
            {
                System.out.println("Nema slobodnih appointmenta");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("DOSAO DO OVDE3!");
            //Find centers for available appointments
            List<Centre> resultCentres = new ArrayList<Centre>();
            List<Centre> centres = new ArrayList<Centre>();
            centreRepository.findAll().forEach(centres::add);
            if (centres.isEmpty())
            {
                System.out.println("Nema centara");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("DOSAO DO OVDE4!");

            for (Centre centre:centres)
            {
                for(Appointment appointment: availableAppointments)
                {
                    if (centre.getId() == appointment.getCentre().getId() && !resultCentres.contains(centre))
                    {
                        resultCentres.add(centre);
                    }
                }

            }
            System.out.println("DOSAO DO OVDE5!");

            if(resultCentres.isEmpty())
            {
                System.out.println("Nema centara sa tim appointmima");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("DOSAO DO OVDE6!");
            return new ResponseEntity<>(resultCentres, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
