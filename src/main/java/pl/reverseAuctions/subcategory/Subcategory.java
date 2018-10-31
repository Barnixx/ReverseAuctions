package pl.reverseAuctions.subcategory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pl.reverseAuctions.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 3, max = 50)
    @Column(name = "subcategory_name", length = 50)
    private String name;

    @NotBlank
    @Length(max = 200)
    @Column(name = "subcategory_description", length = 200)
    private String description;

    @NotNull
    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Category category;
}
