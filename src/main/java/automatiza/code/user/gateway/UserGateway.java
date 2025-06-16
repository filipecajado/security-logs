package automatiza.code.user.gateway;

import automatiza.code.user.dto.UserDTO;

public interface UserGateway {
   Boolean existUserByLogin(String email, String password);

   Long createUser(UserDTO user);
}
