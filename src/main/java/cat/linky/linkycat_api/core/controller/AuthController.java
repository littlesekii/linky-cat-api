package cat.linky.linkycat_api.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linkycat_api.core.dto.AuthLoginUserRequestDTO;
import cat.linky.linkycat_api.core.dto.AuthLoginUserResponseDTO;
import cat.linky.linkycat_api.core.dto.AuthRegisterUserRequestDTO;
import cat.linky.linkycat_api.core.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping("/register/check-username")
    public ResponseEntity<?> registerCheckUsername(@RequestParam String username) {
        authService.registerCheckUsername(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/register/check-email")
    public ResponseEntity<?> registerCheckEmail(@RequestParam String email) {
        authService.registerCheckEmail(email);
        return ResponseEntity.ok().build();
    }
    

}
