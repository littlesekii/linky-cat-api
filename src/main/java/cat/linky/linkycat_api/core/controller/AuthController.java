package cat.linky.linkycat_api.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linkycat_api.core.dto.AuthLoginUserRequestDTO;
import cat.linky.linkycat_api.core.dto.AuthLoginUserResponseDTO;
import cat.linky.linkycat_api.core.dto.AuthRegisterCheckEmailRequestDTO;
import cat.linky.linkycat_api.core.dto.AuthRegisterCheckUsernameRequestDTO;
import cat.linky.linkycat_api.core.dto.AuthRegisterUserRequestDTO;
import cat.linky.linkycat_api.core.service.AuthService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginUserResponseDTO> loginUser(@Valid @RequestBody AuthLoginUserRequestDTO req) {
        String token = authService.authenticate(req.username(), req.password());        

        AuthLoginUserResponseDTO res = new AuthLoginUserResponseDTO(token);
        return ResponseEntity.ok().body(res);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRegisterUserRequestDTO req) {
        authService.register(req.toUserEntity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/check-username")
    public ResponseEntity<?> registerCheckUsername(@Valid @RequestBody AuthRegisterCheckUsernameRequestDTO req) {
        authService.registerCheckUsername(req.username());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/check-email")
    public ResponseEntity<?> registerCheckEmail(@Valid @RequestBody AuthRegisterCheckEmailRequestDTO req) {
        authService.registerCheckEmail(req.email());
        return ResponseEntity.ok().build();
    }
    

}
