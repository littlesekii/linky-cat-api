package cat.linky.linkycat_api.core.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import cat.linky.linkycat_api.core.exception.InvalidEmailVerificationException;
import cat.linky.linkycat_api.core.model.EmailVerification;
import cat.linky.linkycat_api.core.repository.IEmailVerificationRepository;
import cat.linky.linkycat_api.core.util.Utils;

@Service
public class EmailVerificationService {
    
    private final IEmailVerificationRepository repository;

    private final MailService mailService;

    public EmailVerificationService(IEmailVerificationRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    public EmailVerification findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public EmailVerification findByEmailAndVerificationCode(String email, String verificationCode) {
        return repository.findByEmailAndVerificationCode(email, verificationCode);
    }

    public void insert(EmailVerification verification) {
        repository.save(verification);
    }

    public void sendEmailVerificationCode(String email) {

        EmailVerification emailVerification = findByEmail(email);

        if (emailVerification != null && emailVerification.getExpiresAt().isAfter(Instant.now()))
        {
            emailVerification.setExpiresAt(generateExpirationTime());
            insert(emailVerification);

            sendMail(email, emailVerification.getVerificationCode());
            return;
        }

        String verificationCode = generateVerificationCode();        
        insert(new EmailVerification(
            emailVerification == null ? null : emailVerification.getId(), 
            email, 
            verificationCode, 
            false,
            generateExpirationTime()
        ));
        sendMail(email, verificationCode);
        
    }

    public void verifyEmailVerificationCode(String email, String verificationCode) {
        EmailVerification emailVerification = findByEmailAndVerificationCode(email, verificationCode);

        if (emailVerification == null || emailVerification.getExpiresAt().isBefore(Instant.now()))
            throw new InvalidEmailVerificationException("Email verification code is invalid");

        emailVerification.setVerified(true);
        insert(emailVerification);
    }

    public boolean isVerified(String email) {
        EmailVerification emailVerification = findByEmail(email);

        if (emailVerification == null) 
            return false;

        return emailVerification.getVerified();
    }

    private void sendMail(String email, String verificationCode) {
        mailService.sendMail(email, "Email verification code", "Your email verification code: " + verificationCode + "\n\n This code is valid for 10 minutes.");
    }

    private Instant generateExpirationTime() {
        int expirationTimeMillis = 1000 * 60 * 10;
        return Instant.now().plusMillis(expirationTimeMillis);
    }
    private String generateVerificationCode() {
        StringBuilder result = new StringBuilder();

        int ranges[][] = {
            {48, 57}, // '0 to '9' char
            {65, 90}, // 'A' to 'Z' char
        };

        for(int i = 0; i < 6; i++)
        {
            int randomRangeIndex = Utils.randomInt(0, 1);
            int range[] = ranges[randomRangeIndex];

            int randomCharInt = Utils.randomInt(range[0], range[1]);
            result.append((char)randomCharInt);
        }

        return result.toString();
    }
}
