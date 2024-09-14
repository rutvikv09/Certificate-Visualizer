package CertificateVisulizer.CertificateVisulizer.controller;

import CertificateVisulizer.CertificateVisulizer.DTO.ProfileDTO;
import CertificateVisulizer.CertificateVisulizer.model.User;
import CertificateVisulizer.CertificateVisulizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        User loggedInUser = userService.loginUser(email, password);
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok(loggedInUser.getId());
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<ProfileDTO> profile(@RequestParam Long userId) {
        ProfileDTO  profileDTO = userService.userprofile(userId);
        return ResponseEntity.ok(profileDTO);
    }
}
