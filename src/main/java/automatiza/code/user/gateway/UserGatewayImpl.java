package automatiza.code.user.gateway;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import automatiza.code.user.domain.UserEntity;
import automatiza.code.user.dto.UserDTO;
import automatiza.code.user.repository.UserRepository;

@Component
public class UserGatewayImpl implements UserGateway{

    @Autowired
    private UserRepository repository;

    @Override
    public Boolean existUserByLogin(String email, String password) {
        return repository.existsByEmailAndPassword(email, password);
    }

    @Override
    public Long createUser(UserDTO user) {
        if(repository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Usuário já existe com esse email.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        UserEntity entity = new UserEntity(user.getName(),user.getCpf(), LocalDate.now(), encryptedPassword, user.getEmail(), user.getRole());


        return repository.save(entity).getId();
    }
}
