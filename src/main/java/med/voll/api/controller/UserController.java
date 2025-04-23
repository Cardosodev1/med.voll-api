package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.UserDTO;
import med.voll.api.domain.entity.user.User;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid UserDTO userDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

}
