package fr.epsi.petstore.repositories;

import fr.epsi.petstore.bo.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetStoreRepository extends JpaRepository<PetStore, Long> {
}
