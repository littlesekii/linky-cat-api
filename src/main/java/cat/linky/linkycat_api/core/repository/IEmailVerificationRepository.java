package cat.linky.linkycat_api.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.linky.linkycat_api.core.model.EmailVerification;

@Repository
public interface IEmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    EmailVerification findByEmail(String email);
    EmailVerification findByEmailAndVerificationCode(String email, String verificationCode);
}
