package CertificateVisulizer.CertificateVisulizer.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificateTitle;
    private Date issueDate;
    private String organization;
    private String fileUrl;

    @ManyToOne
    private User user;
}
