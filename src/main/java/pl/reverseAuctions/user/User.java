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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPassword
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 5, max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(length = 200)
    private String password;

    @Transient
    private String repeatPassword;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotNull
    @Email
    @Column(name = "user_email", length = 200)
    private String mail;

    @Column(name = "first_name", length = 150)
    @Size(min = 2, max = 150)
    private String firstName;

    @Column(name = "last_name", length = 150)
    @Size(min = 2, max = 150)
    private String lastName;

    private LocalDate birth;

    @Length(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

}