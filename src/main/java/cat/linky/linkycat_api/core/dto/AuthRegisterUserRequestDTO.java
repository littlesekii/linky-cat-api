package cat.linky.linkycat_api.core.dto;

import cat.linky.linkycat_api.core.model.User;

public record AuthRegisterUserRequestDTO(
    String username, 
    String email, 
    String password, 
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
