package cat.linky.linkycat_api.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linkycat_api.core.dto.SendEmailVerificationCodeRequestDTO;
import cat.linky.linkycat_api.core.dto.VerifyEmailVerificationCodeRequestDTO;
import cat.linky.linkycat_api.core.service.EmailVerificationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/email-verification")
public class EmailVerificationController {

    private final EmailVerificationService service;

    public EmailVerificationController(EmailVerificationService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmailVerificationCode(@Valid @RequestBody SendEmailVerificationCodeRequestDTO req) {
        service.sendEmailVerificationCode(req.email());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmailVerificationCode(@Valid @RequestBody VerifyEmailVerificationCodeRequestDTO req) {
        service.verifyEmailVerificationCode(req.email(), req.verificationCode());
        return ResponseEntity.ok().build();
    }
}
