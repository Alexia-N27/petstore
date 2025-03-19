package fr.epsi.petstore.bo;

import fr.epsi.petstore.enums.ProdType;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="code")
    private String code;
    @Column(name="label")
    private String label;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ProdType type;
    @Column(name="price")
    private Double price;

    @ManyToMany
    @JoinTable(name="Pro_Pet",
        joinColumns = @JoinColumn(name="id_pro", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="id_pet", referencedColumnName = "id")
    )
    private Set<PetStore> petStores;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
