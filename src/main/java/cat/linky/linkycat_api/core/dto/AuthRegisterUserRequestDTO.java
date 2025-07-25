package cat.linky.linkycat_api.core.dto;

import cat.linky.linkycat_api.core.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterUserRequestDTO(

    @NotBlank(message = "Property \"username\" must not be blank")
    String username, 

    @Email(message = "Property \"email\" has a invalid format")
    @NotBlank(message = "Property \"email\" must not be blank")
    String email, 

    @NotBlank(message = "Property \"password\" must not be blank")
    String password, 

    @NotBlank(message = "Property \"first name\" must not be blank")
    String firstName, 
    String lastName
) {

    public User toUserEntity() {
        return new User(
            null,
            username,
            email,
            password,
            firstName,
            lastName
        );
    }

}
