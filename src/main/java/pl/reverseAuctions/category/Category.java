package pl.reverseAuctions.category;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.auction.attribiute.AttributeGroup;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "category_name", length = 50)
    @JsonProperty(value = "name")
    private String categoryName;

    @NotBlank
    @Length(max = 250)
    @Column(name = "category_description", columnDefinition = "TEXT", length = 200)
    @JsonIgnore
    private String categoryDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @JsonIgnoreProperties(value = "children_categories", allowSetters = true)
    @JsonProperty(value = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parentCategory;

    @OneToMany(
            mappedBy = "parentCategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    //@JsonIgnore
    //@JsonIgnoreProperties(value = "parent_id", allowSetters=true)
    @JsonProperty(value = "children_categories")
    private Set<Category> subcategories;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "categories_attribute_group",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_group_id")}
    )
    private List<AttributeGroup> attributeGroups;
}
