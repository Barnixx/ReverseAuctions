package pl.reverseAuctions.model.user;

import java.sql.Date;

public class User {
    private Long idUser;
    private String login;
    private String firstName;
    private String lastName;
    private Date birth;
    private String mail;
    private UserRole userRole;

    public User() {
    }

    public User(String login, String firstName, String lastName, Date birth, String mail, UserRole userRole) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.mail = mail;
        this.userRole = userRole;
    }

    public User(Long idUser, String login, String firstName, String lastName, Date birth, String mail, UserRole userRole) {
        this.idUser = idUser;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.mail = mail;
        this.userRole = userRole;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getUserRole() {
        return idUserRole;
    }

    public void setUserRole(UserRole idUserRole) {
        this.idUserRole = idUserRole;
    }

}