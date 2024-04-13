package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Dtos.ChangeInitialPasswordDto;
import com.example.ISA.tim6.Dtos.LoginRequestDto;
import com.example.ISA.tim6.ENUMS.Role;
import com.example.ISA.tim6.Model.User;
import com.example.ISA.tim6.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("USEAO");
        try
        {
            System.out.println("USEAO1");
            List<User> users = new ArrayList<User>();
            System.out.println("USEAO2");
            userRepository.findAll().forEach(users::add);
            System.out.println("USEAO3");
            if (users.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getUserById(long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> createUser(User newUser) {

        try {
            if(newUser.getRole() == Role.ADMINISTRATOR_SISTEMA)
            {
                var user = new User(newUser.getName(), newUser.getSurname(), newUser.getEmail(),  newUser.getRole(), newUser.getPassword(), newUser.getPoints());
                user.setUsername(newUser.getUsername());
                user.setCity(newUser.getCity());
                user.setCompanyName(newUser.getCompanyName());
                user.setCounty(newUser.getCountry());
                user.setRole(Role.ADMINISTRATOR_SISTEMA);
                user.setGender(newUser.getGender());
                user.setPhoneNumber(newUser.getPhoneNumber());
                user.setJob(newUser.getJob());
                user.setStreet(newUser.getStreet());
                User _user = userRepository
                        .save(user);

                return new ResponseEntity<>(_user, HttpStatus.CREATED);
            }
            else if(newUser.getRole()==Role.KORISNIK)
            {
                User _user = userRepository
                		.save(new User(newUser.getUsername(), newUser.getName(), newUser.getSurname(), newUser.getEmail(), newUser.getPassword(), newUser.getPoints(), 
                        		newUser.getRole(), newUser.getGender(), newUser.getPhoneNumber(), newUser.getJmbg(),newUser.getJob(), newUser.getCompanyName(),
                        		newUser.getNumber(), newUser.getStreet(), newUser.getCity(), newUser.getCountry()));
                		
                return new ResponseEntity<>(_user, HttpStatus.CREATED);
            }
            else if(newUser.getRole()==Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE)
            {
                User _user = userRepository
                        .save(new User(newUser.getName(), newUser.getSurname(), newUser.getEmail(),  newUser.getRole(), newUser.getPassword()));
                return new ResponseEntity<>(_user, HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(User user) {
        try {

            Optional<User> userData = userRepository.findById(user.getId());

            if (userData.isPresent())
            {
                User _user = userData.get();
                //Validations
                if(!user.getEmail().equals(_user.getEmail()))
                {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //Validation good, change info!
                _user = user;

                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> updateUser(long id, User user){

        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            //Validations
            if(!user.getEmail().equals(_user.getEmail()))
            {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            //Validation good, change info!
            _user = user;

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteUser(long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> findAllByRole(Role administratorCentraIMedicinskoOsoblje) {
        try
        {
            List<User> users = userRepository.findAllByRole(Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> login(LoginRequestDto requestBody) {
        return userRepository.findByUsernameAndPassword(requestBody.getUsername(), requestBody.getPassword())
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> changeInitialPassword(ChangeInitialPasswordDto requestBody) {
        var optUser = userRepository.findById(requestBody.getUserId());
        if (optUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var user = optUser.get();

        if (!user.getPassword().equals(requestBody.getOldPassword())) {
            return ResponseEntity.badRequest().build();
        }

        user.setChangedInitialPassword(true);
        user.setPassword(requestBody.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<User>> getMedicalWorkersByCentre(Long centreId) {
        List<User> allByRole = userRepository.findAllByRole(Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE);
        return ResponseEntity.ok(allByRole.stream().filter(x -> x.getCenterId() == centreId).toList());
    }

    /*public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
