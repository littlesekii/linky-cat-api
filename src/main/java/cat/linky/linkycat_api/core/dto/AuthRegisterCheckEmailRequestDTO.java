package cat.linky.linkycat_api.core.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterCheckEmailRequestDTO(
    @NotBlank(message = "Property \"email\" must not be blank")
    String email
) {}
