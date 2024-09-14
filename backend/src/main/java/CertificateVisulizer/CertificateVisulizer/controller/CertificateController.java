package CertificateVisulizer.CertificateVisulizer.controller;

import CertificateVisulizer.CertificateVisulizer.DTO.RequestCertificateDTO;
import CertificateVisulizer.CertificateVisulizer.model.Certificate;
import CertificateVisulizer.CertificateVisulizer.model.User;
import CertificateVisulizer.CertificateVisulizer.service.CertificateService;
import CertificateVisulizer.CertificateVisulizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/get")
    public ResponseEntity<List<Certificate>> getCertificates(@RequestParam Long userId) {
        User user = userService.findById(userId);
        List<Certificate> certificates = certificateService.getCertificate(user);
        return ResponseEntity.ok(certificates);
    }

   @CrossOrigin
  @PostMapping("/upload")
  public ResponseEntity<Certificate> uploadCertificate(
          @RequestParam("certificateTitle") String certificateTitle,
          @RequestParam("issueDate") String issueDateString,
          @RequestParam("organization") String organization,
          @RequestParam("file") MultipartFile file,
          @RequestParam("userId") Long userId) throws IOException, ParseException {

      Date issueDate = new SimpleDateFormat("yyyy-MM-dd").parse(issueDateString);

      Certificate certificate = certificateService.addCertificate(file,certificateTitle,organization,issueDate,userId);
      return ResponseEntity.ok(certificate);
  }

}
