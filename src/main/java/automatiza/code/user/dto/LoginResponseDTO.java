package automatiza.code.user.dto;


public class LoginResponseDTO {
    private String token;

	public String getToken() {
		return token;
	}

	public LoginResponseDTO(String token) {
		this.token = token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
