package fr.epsi.petstore.repositories;

import fr.epsi.petstore.bo.Animal;
import fr.epsi.petstore.bo.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE a.petStore = :petStore")
    List<Animal> findByPetStore(@Param("petStore") PetStore petStore);
}
