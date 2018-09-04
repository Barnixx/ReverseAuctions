package pl.reverseAuctions.auction.attribiute;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attribute")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long id;

    @Column(name = "attribute_name")
    private String name;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "attribute_attribute_group",
            joinColumns = {@JoinColumn(name = "attribute_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_group_id")}
    )
    private List<AttributeGroup> attributeGroups;

    @OneToMany(mappedBy = "attribute")
    private List<AttributeValue> attributeValues;
}
