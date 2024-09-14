package CertificateVisulizer.CertificateVisulizer.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;
    private String fullName;
    private String email;
    private String posting;
    private String about;

    private List<String> certificateTitle;
    private List<CertificateProfileDTO> certificateProfile;

}
