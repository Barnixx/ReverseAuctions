package pl.reverseAuctions.auction.attribiute;

import javax.persistence.*;

@Entity
@Table(name = "attribute_dictionary")
public class AttributeDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "attribute_dictionary_value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;
}
