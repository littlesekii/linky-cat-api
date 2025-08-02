package cat.linky.linkycat_api.core.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterCheckUsernameRequestDTO(
    @NotBlank(message = "Property \"username\" must not be blank")
    String username
) {}
