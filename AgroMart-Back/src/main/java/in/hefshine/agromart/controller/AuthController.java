package in.hefshine.agromart.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.hefshine.agromart.dto.LoginRequest;
import in.hefshine.agromart.dto.RegisterRequest;  

import in.hefshine.agromart.dto.UserResponse;
import in.hefshine.agromart.entity.User;
import in.hefshine.agromart.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
//(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AuthController {
    @Autowired
    private UserService userService;
    

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        UserResponse response = userService.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
