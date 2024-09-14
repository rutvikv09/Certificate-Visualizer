package CertificateVisulizer.CertificateVisulizer.DTO;


import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCertificateDTO {
    private String certificateTitle;
    private String issueDateString;
    private String organization;
    private Long userId;
}
