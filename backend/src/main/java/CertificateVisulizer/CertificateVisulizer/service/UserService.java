package CertificateVisulizer.CertificateVisulizer.service;

import CertificateVisulizer.CertificateVisulizer.DTO.CertificateProfileDTO;
import CertificateVisulizer.CertificateVisulizer.DTO.ProfileDTO;
import CertificateVisulizer.CertificateVisulizer.model.User;
import CertificateVisulizer.CertificateVisulizer.repository.CertificateRepository;
import CertificateVisulizer.CertificateVisulizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CertificateService certificateService;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists cannot be registered again!";
        }

        userRepository.save(user);
        return "User registered successfully";
    }



    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> password.equals(user.getPassword()))
                .orElse(null); // Return null if credentials are invalid
    }



    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    //get all the profile details

    public ProfileDTO userprofile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
       List<CertificateProfileDTO> allCertificates = certificateService.getCertificates(user);

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setFullName(user.getFullName());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setPosting(user.getPosting());
        profileDTO.setAbout(user.getAbout());
        profileDTO.setCertificateTitle(new ArrayList<>());
        profileDTO.setCertificateTitle(allCertificates.stream().map(CertificateProfileDTO::getCertificateTitle).collect(Collectors.toList()));
        profileDTO.setCertificateProfile(allCertificates);

        return profileDTO;
    }


}
