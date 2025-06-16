package automatiza.code.user.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import automatiza.code.user.domain.UserEntity;
import automatiza.code.user.dto.UserResponseDTO;
import automatiza.code.user.repository.UserRepository;

@Service
public class AuthorizationServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public UserResponseDTO getUserAuthenticated() {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return new UserResponseDTO(user.getId(), user.getEmail(), user.getName());

        } catch (Exception e) {
            throw new RuntimeException("Não autorizado", e);
        }
    }
}
