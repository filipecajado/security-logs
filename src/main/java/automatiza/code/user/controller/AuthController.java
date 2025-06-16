package automatiza.code.user.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import automatiza.code.infra.security.TokenService;
import automatiza.code.user.domain.UserEntity;
import automatiza.code.user.dto.LoginDTO;
import automatiza.code.user.dto.LoginResponseDTO;
import automatiza.code.user.dto.UserResponseDTO;
import automatiza.code.user.gateway.AuthorizationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationServiceImpl authorizationServiceImpl;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/logins")
    public ResponseEntity<LoginResponseDTO> authLogin(@RequestBody @Valid LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@RequestBody @Valid LoginDTO loginDTO, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        try {
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

            log.info("Login bem-sucedido | Usuário: {} | IP: {} | Horário: {}", loginDTO.getEmail(), clientIp, LocalDateTime.now());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (BadCredentialsException e) {
            log.warn("Tentativa de login inválida | Usuário: {} | IP: {} | Horário: {}", loginDTO.getEmail(), clientIp, LocalDateTime.now());
            return ResponseEntity.status(401).body("Credenciais inválidas");
        } catch (AuthenticationException e) {
            log.error("Erro inesperado durante autenticação | Usuário: {} | IP: {} | Erro: {} | Horário: {}", loginDTO.getEmail(), clientIp, e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(500).body("Erro interno na autenticação");
        }
    }

    @GetMapping("/userAuthenticated")
    public ResponseEntity<UserResponseDTO> getUserAuthenticate() {
        return ResponseEntity.ok(this.authorizationServiceImpl.getUserAuthenticated());
    }
}
