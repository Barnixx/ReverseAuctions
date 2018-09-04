package pl.reverseAuctions.auction;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import pl.reverseAuctions.auction.attribiute.AttributeValue;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.subcategory.Subcategory;
import pl.reverseAuctions.user.User;
import pl.reverseAuctions.validator.FutureDate;
import pl.reverseAuctions.validator.NewAuctionValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @NotBlank(message = "Tytuł nie możę być pusty")
    @Length(min = 10, max = 100, message = "Tytuł musi mięć od {min} do {max} znaków")
    @Column(name = "auction_name", length = 100)
    private String name;

    @NotBlank(message = "Opis nie może być pusty")
    @Length(max = 800, message = "Opis może mieć maksymalnie {max} znaków")
    @Column(name = "auction_description", columnDefinition = "TEXT", length = 800)
    private String description;

    @NotNull(message = "Podkategoria nie może być pusta")
    @ManyToOne(targetEntity = Subcategory.class)
    private Subcategory subcategory;

    @ManyToOne(targetEntity = User.class)
    private User user;


    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Setter(AccessLevel.NONE)
    @Column(name = "auction_created", updatable = false)
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Setter(AccessLevel.NONE)
    @Column(name = "auction_updated")
    private LocalDate updated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data nie może być pusta")
    @Column(name = "auction_start_time")
    private LocalDate startTime;

    @FutureDate(message = "Podaj przyszłą datę", groups = NewAuctionValidationGroup.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data nie może być pusta")
    @Column(name = "auction_end_time")
    private LocalDate endTime;

    @Column(name = "auction_is_active")
    private byte isActive;

    @Column(name = "auction_view")
    private Long view;

    @OneToOne(orphanRemoval = true)
    private Offer winOffer;

    @Column(name = "auction_imageUrl")
    private String imageUrl;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "auction_attribute_value",
            joinColumns = {@JoinColumn(name = "auction_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_value_id")}
    )
    private List<AttributeValue> attributeValues;

    @PrePersist
    void created() {
        this.view = 0L;
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void updated() {
        this.updated = LocalDate.now();
    }
}
