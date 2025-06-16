package automatiza.code.user.dto;


public class LoginDTO {
    private String email;
    private String password;
    
	public LoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
