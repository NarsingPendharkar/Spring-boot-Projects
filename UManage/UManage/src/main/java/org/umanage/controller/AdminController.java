package org.umanage.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.flogger.Flogger;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
@Tag(name = "User Management by ADMIN", description = "APIs for managing users by ADMIN")
public class AdminController {
	
	private static final Logger Flogger=LogManager.getLogger(AdminController.class);

    @Autowired
    private UserdataService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Operation(
        summary = "Register new user",
        description = "Creates and returns a new user if the email does not already exist"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input or duplicate email",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        )
    })
    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody @Valid AppUser newUser)
            throws MessagingException, IOException, DocumentException {

        if (newUser.getId() != null) {
            return ResponseEntity.badRequest().body("Don't send user ID!");
        }

        if (userService.emailExist(newUser.getEmail())) {
            throw new UserAlreadyExistsException("User already registered with email: " + newUser.getEmail());
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        AppUser savedUser = userService.register(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(savedUser, AppUserResponse.class));
    }

    @Operation(summary = "List all users", description = "Returns a list of all registered users")
    @GetMapping("/list")
    public List<AppUserResponse> getAllUsers() {
    	 Flogger.info("get list of users called !");
        return userService.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, AppUserResponse.class))
                .toList();
    }

    @Operation(summary = "Update user by ID", description = "Updates an existing user based on their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid AppUser userdata, @PathVariable Long id) {
        userdata.setPassword(passwordEncoder.encode(userdata.getPassword()));
        AppUser updated = userService.updateUser(userdata, id);
        return ResponseEntity.ok(modelMapper.map(updated, AppUserResponse.class));
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user from the system based on the provided ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by ID", description = "Fetches a single user based on their ID")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @GetMapping("/{id}")
    public AppUserResponse getUserById(
            @Parameter(description = "ID of the user to fetch") @PathVariable Long id) {
        return modelMapper.map(userService.getUserById(id), AppUserResponse.class);
    }
}
