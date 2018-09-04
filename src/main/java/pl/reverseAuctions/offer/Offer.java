package pl.reverseAuctions.offer;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.auction.Auction;
import pl.reverseAuctions.auction.attribiute.AttributeValue;
import pl.reverseAuctions.user.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 10, max = 100)
    @Column(name = "offer_title")
    private String title;

    @Length(max = 800)
    @Column(name = "offer_description", columnDefinition = "TEXT", length = 800)
    private String description;

    @NotNull
    @Digits(integer = 9, fraction = 2)
    @Column(name = "offer_price", precision = 9, scale = 2)
    private Double price;

    @NotNull
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    private User user;

    @NotNull
    @ManyToOne(targetEntity = Auction.class, cascade = CascadeType.REMOVE)
    private Auction auction;

    @Setter(AccessLevel.NONE)
    @Column(name = "offer_created")
    private LocalDateTime created;

    @Setter(AccessLevel.NONE)
    @Column(name = "offer_updated")
    private LocalDateTime updated;

    @Column(name = "offer_is_active")
    private boolean isActive;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "offer_attribute_value",
            joinColumns = {@JoinColumn(name = "offer_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_value_id")}
    )
    private List<AttributeValue> attributeValues;

    @PrePersist
    void created() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void updated() {
        this.updated = LocalDateTime.now();
    }
}
