package fr.epsi.petstore.bo;

import fr.epsi.petstore.enums.FishLivEnv;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Fish extends Animal {

    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;
}
