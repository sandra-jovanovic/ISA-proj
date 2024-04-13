package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Dtos.ChangeInitialPasswordDto;
import com.example.ISA.tim6.Dtos.LoginRequestDto;
import com.example.ISA.tim6.ENUMS.Role;
import com.example.ISA.tim6.Model.User;
import com.example.ISA.tim6.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> getAllCentreAdmins(@RequestBody LoginRequestDto requestBody) {
        return userService.login(requestBody);
    }

    @PostMapping("/users/initial-password/change")
    public ResponseEntity<?> changeInitialPassword(@RequestBody ChangeInitialPasswordDto requestBody) {
        return userService.changeInitialPassword(requestBody);
    }

    @GetMapping("/users/centre-admins")
    public ResponseEntity<List<User>> getAllCentreAdmins() {
        return userService.findAllByRole(Role.ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {

        return userService.getUserById(id);
    }

    @GetMapping("/users/medical-workers/{centreId}")
    public ResponseEntity<List<User>> getMedicalWorkersByCentre(@PathVariable Long centreId) {

        return userService.getMedicalWorkersByCentre(centreId);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser)
    {
        return userService.createUser(newUser);
    }

    @PostMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return userService.updateUser(user.getId(), user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
       return userService.deleteUser(id);
    }

   /* @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        return userService.deleteAllUsers();
    }*/

}
