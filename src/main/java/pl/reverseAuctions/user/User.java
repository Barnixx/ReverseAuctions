package pl.reverseAuctions.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.role.Role;
import pl.reverseAuctions.validator.ConfirmPassword;
import pl.reverseAuctions.validator.UniqeUserName;
import pl.reverseAuctions.validator.UniqueUserEmail;
import pl.reverseAuctions.validator.UserRegisterValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPassword(message = "Hasła są różne")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UniqeUserName(message = "Podany login jest już zajęty", groups = UserRegisterValidationGroup.class)
    @NotBlank(message = "Login nie może być pusty")
    @Length(min = 5, max = 20, message = "Login powinien mieć od {min} do {max} znaków")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @Column(length = 200)
    private String password;

    @Transient
    private String repeatPassword;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @UniqueUserEmail(message = "Podany email już istnieje", groups = UserRegisterValidationGroup.class)
    @NotBlank(message = "Email nie możę być pusty")
    @Email(message = "Wprowadź poprawny adres email")
    @Column(name = "user_email", length = 200)
    private String mail;

    @Column(name = "first_name", length = 150)
    @Size(min = 2, max = 150, message = "Imie może mieć od {min} do {max} znaków")
    private String firstName;

    @Column(name = "last_name", length = 150)
    @Size(min = 2, max = 150, message = "Nazwisko możę mieć od {min} do {max} znaków")
    private String lastName;

    private LocalDate birth;

    @Length(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

}