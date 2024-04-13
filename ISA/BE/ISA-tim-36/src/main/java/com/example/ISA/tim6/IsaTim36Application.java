package com.example.ISA.tim6;

import com.example.ISA.tim6.ENUMS.Role;
import com.example.ISA.tim6.Model.*;
import com.example.ISA.tim6.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class IsaTim36Application {

	public static void main(String[] args) {
		SpringApplication.run(IsaTim36Application.class, args);
		}


	@Bean
	public CommandLineRunner loadComplaintsData(ComplaintsRepository repository, UserRepository userRepository,
												CentreRepository centreRepository, AppointmentRepository appointmentRepository, EquipmentRepository equipmentRepository,BloodTypesRepository bloodTypesRepository) {
		return (args) -> {



			var user1 = new User("a","a",1, "Pera", "Peric", "a@a.com", 2);

			user1.setRole(Role.ADMINISTRATOR_SISTEMA);

			var user2 = new User("mr","mr",2, "Medicinski", "Radnik2", "m@m.com", 21);
			user2.setCenterId(6);
			user2.setRole(Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE);

			var user3 = new User("k1","k1",3, "Korisnik", "1", "m@m.com", 21);
			user3.setRole(Role.KORISNIK);

			var user4 = new User("k","k",4, "Korisnik2", "KorisnikPrezimic", "am@m.com", 12);
			user4.setRole(Role.KORISNIK);

			var user5 = new User("am1","am1",5, "Medicinski", "Radnik3", "bm@m.com", 21);
			user5.setCenterId(6);
			user5.setRole(Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE);


			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);


			var centre = new Centre("testC", "Vracar", "testNum", "testDesc", 7, new ArrayList<>());
			centreRepository.save(centre);

			var complaint1 = new Complaint("test", "test");
			complaint1.setMedicalWorker(user2);
			complaint1.setUser(user3);
			complaint1.setCentre(centre);
			repository.save(complaint1);

			var appointment = new Appointment();
			appointment.setInfo("TEST");
			appointment.setDateTime(LocalDateTime.now());
			appointment.setDurationInMinutes(40);
			appointment.setCentre(centre);
			appointment.setMedicalWorker(user5);
			appointment.setUser(user3);

			var  appointment2 = new Appointment();
			appointment2.setInfo("NoviTest");
			appointment2.setDateTime(LocalDateTime.now().plusDays(3));
			appointment2.setDurationInMinutes(45);
			appointment2.setCentre(centre);
			appointment2.setMedicalWorker(user2);
			appointment2.setUser(user4);

			appointmentRepository.save(appointment);
			appointmentRepository.save(appointment2);


			var equipment = new Equipment("Zavoj", 6);
			var equipment2 = new Equipment("Spric", 5);
			var equipment3 = new Equipment("Operacioni_sto", 12);
			var equipment4 = new Equipment("EKG_masina", 7);
			equipmentRepository.save(equipment);
			equipmentRepository.save(equipment2);
			equipmentRepository.save(equipment3);
			equipmentRepository.save(equipment4);


			var bloodTypes1 =new BloodTypes("A+", 10);
			var bloodTypes2 =new BloodTypes("A-", 10);
			var bloodTypes3 =new BloodTypes("B+", 10);
			var bloodTypes4 =new BloodTypes("B-", 10);
			var bloodTypes5 =new BloodTypes("O+", 10);
			var bloodTypes6 =new BloodTypes("O-", 10);
			var bloodTypes7 =new BloodTypes("AB+", 10);
			var bloodTypes8 =new BloodTypes("AB-", 10);
			bloodTypesRepository.save(bloodTypes1);
			bloodTypesRepository.save(bloodTypes2);
			bloodTypesRepository.save(bloodTypes3);
			bloodTypesRepository.save(bloodTypes4);
			bloodTypesRepository.save(bloodTypes5);
			bloodTypesRepository.save(bloodTypes6);
			bloodTypesRepository.save(bloodTypes7);
			bloodTypesRepository.save(bloodTypes8);











		};
	}

}
