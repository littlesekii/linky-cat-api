package cat.linky.linkycat_api.core.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginUserRequestDTO(

    @NotBlank(message = "Property \"username\" must not be blank")
    String username, 
    
    @NotBlank(message = "Property \"password\" must not be blank")
    String password
) {}
