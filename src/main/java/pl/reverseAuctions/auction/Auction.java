package pl.reverseAuctions.auction;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.subcategory.Subcategory;
import pl.reverseAuctions.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 10, max = 100)
    @Column(name = "auction_name", length = 100)
    private String name;

    @NotBlank
    @Length(max = 800)
    @Column(name = "auction_description", columnDefinition = "TEXT", length = 800)
    private String description;

    @NotNull
    @ManyToOne(targetEntity = Subcategory.class)
    private Subcategory subcategory;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Setter(AccessLevel.NONE)
    @NotNull
    @Column(name = "auction_created", updatable = false)
    private LocalDateTime created;

    @Setter(AccessLevel.NONE)
    @Column(name = "auction_updated")
    private LocalDateTime updated;

    @NotNull
    @Column(name = "auction_start_time")
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "auction_end_time")
    private LocalDateTime endTime;

    @Column(name = "auction_is_active")
    private byte isActive;

    @Column(name = "auction_view")
    private Long view;

    @OneToOne(orphanRemoval = true)
    private Offer winOffer;

    @Column(name = "auction_imageUrl")
    private String imageUrl;

    @PrePersist
    void created() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void updated() {
        this.updated = LocalDateTime.now();
    }
}
