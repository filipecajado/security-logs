package automatiza.code.user.repository;

import automatiza.code.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Boolean existsByEmailAndPassword(String email, String password);

	UserDetails findByEmail(String email);
}
