package org.umanage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.umanage.dto.AppUserResponse;
import org.umanage.entity.AppUser;
import org.umanage.exception.UserAlreadyExistsException;
import org.umanage.service.UserdataService;
import org.umanage.util.EmailService;

import com.itextpdf.text.DocumentException;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private UserdataService userService;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private ModelMapper modelMapper;
    @Autowired private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody @Valid AppUser newUser) throws MessagingException, IOException, DocumentException {
        if (newUser.getId() != null) {
            return ResponseEntity.badRequest().body("Don't send user ID!");
        }

        if (userService.emailExist(newUser.getEmail())) {
            throw new UserAlreadyExistsException("User already registered with email: " + newUser.getEmail());
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        AppUser savedUser = userService.register(newUser);
        emailService.sendWelcomeMailWithPdf("narsingpen@gmail.com", savedUser.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedUser, AppUserResponse.class));
    }

    @GetMapping("/list")
    public List<AppUserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> modelMapper.map(user, AppUserResponse.class))
                .toList();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid AppUser userdata, @PathVariable Long id) {
        userdata.setPassword(passwordEncoder.encode(userdata.getPassword()));
        AppUser updated = userService.updateUser(userdata, id);
        return ResponseEntity.ok(modelMapper.map(updated, AppUserResponse.class));
    }

   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public AppUserResponse getUserById(@PathVariable Long id) {
        return modelMapper.map(userService.getUserById(id), AppUserResponse.class);
    }
}
