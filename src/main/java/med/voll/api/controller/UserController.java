package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.UserDTO;
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

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid UserDTO userDTO) {
        var token = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
