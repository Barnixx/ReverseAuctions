package src.main.java.pl.reverseAuctions.model.userRole;

public class UserRole {

    private Long id;
    private String userRoleName;

    public UserRole() {
    }

    public UserRole(Long id, String userRoleName) {
        this.id = id;
        this.userRoleName = userRoleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
