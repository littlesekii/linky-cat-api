package cat.linky.linkycat_api.core.dto;

public record AuthLoginUserRequestDTO(
    String username, 
    String password
) {}
