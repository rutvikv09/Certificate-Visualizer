package CertificateVisulizer.CertificateVisulizer.service;

import CertificateVisulizer.CertificateVisulizer.DTO.CertificateProfileDTO;
import CertificateVisulizer.CertificateVisulizer.model.Certificate;
import CertificateVisulizer.CertificateVisulizer.model.User;
import CertificateVisulizer.CertificateVisulizer.repository.CertificateRepository;
import CertificateVisulizer.CertificateVisulizer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private S3Client s3Client;

    public List<Certificate> getCertificate(User user) {
        List<Certificate> cert  = certificateRepository.findByUser(user);
        return cert;
    }

    public List<CertificateProfileDTO> getCertificates(User user) {
        List<Certificate> certificates = certificateRepository.findByUser(user);
        return certificates.stream()
                .map(certificate -> modelMapper.map(certificate, CertificateProfileDTO.class))
                .collect(Collectors.toList());
    }

    public Certificate addCertificate(MultipartFile file, String certificateTitle, String organization, Date issueDate, Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String fileUrl = uploadFileToS3(file);

        Certificate certificate = new Certificate();
        certificate.setCertificateTitle(certificateTitle);
        certificate.setIssueDate(issueDate);
        certificate.setOrganization(organization);
        certificate.setFileUrl(fileUrl);
        certificate.setUser(user);

        return certificateRepository.save(certificate);
    }


    private String uploadFileToS3(MultipartFile file) throws IOException {
        String bucketName = "cardportfolioservices";
        String fileName = file.getOriginalFilename();

        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            // Generate URL for the uploaded file
            URL url = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName));
            return url.toString();
        } catch (S3Exception e) {
            throw new RuntimeException("Error uploading file to S3: " + e.getMessage(), e);
        }
    }

}
