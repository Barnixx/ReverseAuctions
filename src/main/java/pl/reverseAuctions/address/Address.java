package pl.reverseAuctions.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.reverseAuctions.user.User;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String country;

    @Column(length = 150)
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(length = 200)
    private String street;

    @ManyToOne
    private User user;
}
