package pl.reverseAuctions.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "category_name", length = 50)
    private String categoryName;

    @NotBlank
    @Length(max = 250)
    @Column(name = "category_description", columnDefinition = "TEXT", length = 200)
    private String categoryDescription;
}
