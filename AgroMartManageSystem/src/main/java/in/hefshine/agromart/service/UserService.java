package in.hefshine.agromart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.hefshine.agromart.dto.RegisterRequest;
import in.hefshine.agromart.dto.UserResponse;
import in.hefshine.agromart.entity.User;
import in.hefshine.agromart.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse registerUser(RegisterRequest registerRequest) {
        // Create a new User object
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(registerRequest.getPassword()); // Store password as plain text

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Create and return the response DTO
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setPhoneNumber(savedUser.getPhoneNumber());

        return response;
    }

    public Optional<User> loginUser(String email, String password) {
        // Find the user by email
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) { // Compare plain text password
            return user;
        }
        return Optional.empty();
    }
}
