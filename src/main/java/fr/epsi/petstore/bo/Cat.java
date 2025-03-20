package fr.epsi.petstore.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Cat")
public class Cat extends Animal {

    @Column(name="chip_id")
    private String chipId;

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
}
