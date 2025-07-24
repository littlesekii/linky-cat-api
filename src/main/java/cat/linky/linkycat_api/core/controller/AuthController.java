package cat.linky.linkycat_api.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linkycat_api.core.dto.AuthRegisterUserRequestDTO;
import cat.linky.linkycat_api.core.dto.StatusMessageResponseDTO;
import cat.linky.linkycat_api.core.service.UserService;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
        
    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthRegisterUserRequestDTO req) {
        userService.insert(req.toUserEntity());
        return ResponseEntity.ok().build();
    }

}
