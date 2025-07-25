package cat.linky.linkycat_api.core.dto;

import java.time.Instant;

public record StandardErrorResponseDTO (
    Instant moment,
    Integer statusCode,
    String error,
    String message,
    String path
) {}
