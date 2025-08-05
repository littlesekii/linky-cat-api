package cat.linky.linkycat_api.core.dto;

import jakarta.validation.constraints.NotBlank;

public record VerifyEmailVerificationCodeRequestDTO(
    @NotBlank(message = "Property \"email\" must not be blank")
    String email,
    @NotBlank(message = "Property \"verificationCode\" must not be blank")
    String verificationCode
) {}
