package CertificateVisulizer.CertificateVisulizer.repository;

import CertificateVisulizer.CertificateVisulizer.model.Certificate;
import CertificateVisulizer.CertificateVisulizer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUser(User user);
}
