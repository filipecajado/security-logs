package automatiza.code.user.domain;


public enum UserRole {

    ADMIN("admin"),
    USER("user");
   private String role;

   UserRole(String role){
        this.setRole(role);
    }

   public String getRole() {
	return role;
}

   public void setRole(String role) {
	this.role = role;
}
   
}
