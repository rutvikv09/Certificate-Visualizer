package CertificateVisulizer.CertificateVisulizer.DTO;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateProfileDTO {

    private String certificateTitle;
    private Date issueDate;
    private String fileUrl;
}
