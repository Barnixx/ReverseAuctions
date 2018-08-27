package pl.reverseAuctions.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.userRole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 5, max = 20)
    private String login;

    @NotBlank
    @Column(length = 200)
    private String password;

    @NotNull
    @Email
    @Column(name = "user_email", length = 200)
    private String mail;

    @NotBlank
    @Column(name = "first_name", length = 150)
    @Size(min = 2, max = 150)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", length = 150)
    @Size(min = 2, max = 150)
    private String lastName;

    @Past
    private LocalDate birth;

    @Length(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @NotNull
    @OneToOne
    private UserRole userRole;
}