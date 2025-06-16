package automatiza.code.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import automatiza.code.user.dto.UserDTO;
import automatiza.code.user.gateway.UserGateway;
import jakarta.validation.Valid;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
	private  UserGateway userGateway;

    @PostMapping("/register")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserDTO user) {
        Long saveUser = userGateway.createUser(user);
        return ResponseEntity.ok(saveUser);
    }
}
