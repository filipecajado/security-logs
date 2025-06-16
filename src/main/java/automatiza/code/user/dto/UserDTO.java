package automatiza.code.user.dto;

import automatiza.code.user.domain.UserRole;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private UserRole role;
    
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getCpf() {
		return cpf;
	}
	public UserRole getRole() {
		return role;
	}
	
	public UserDTO(String name, String email, String password, String cpf, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.role = role;
	}
	
}
