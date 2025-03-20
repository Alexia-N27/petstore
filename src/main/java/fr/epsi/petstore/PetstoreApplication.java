package fr.epsi.petstore;

import fr.epsi.petstore.bo.Address;
import fr.epsi.petstore.bo.Cat;
import fr.epsi.petstore.bo.PetStore;
import fr.epsi.petstore.bo.Product;
import fr.epsi.petstore.enums.ProdType;
import fr.epsi.petstore.repositories.AddressRepository;
import fr.epsi.petstore.repositories.AnimalRepository;
import fr.epsi.petstore.repositories.PetStoreRepository;
import fr.epsi.petstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class PetstoreApplication implements CommandLineRunner {

    private PetStoreRepository petStoreRepository;
    private ProductRepository productRepository;
    private AnimalRepository animalRepository;
    private AddressRepository addressRepository;

    @Autowired
    public PetstoreApplication(PetStoreRepository petStoreRepository, ProductRepository productRepository, AnimalRepository animalRepository, AddressRepository addressRepository) {
        this.petStoreRepository = petStoreRepository;
        this.productRepository = productRepository;
        this.animalRepository = animalRepository;
        this.addressRepository = addressRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PetstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer 3 adresses
        Address address1 = createAndSaveAddress("12", "Main Street", "75000", "Paris");
        Address address2 = createAndSaveAddress("45", "Broadway Avenue", "69000", "Lyon");
        Address address3 = createAndSaveAddress("8", "Liberty Road", "33000", "Bordeaux");

        // Créer 3 animaleries
        PetStore store1 = createAndSavePetStore("Happy Pets", "Johny", address1);
        PetStore store2 = createAndSavePetStore("Animal Paradise", "Sarah", address2);
        PetStore store3 = createAndSavePetStore("Exotic Friends", "Mike", address3);

        // Créer 3 produits par animalerie
        createAndSaveProduct("FD123", "Dog Food", ProdType.FOOD, 10.5, store1);
        createAndSaveProduct("AC456", "Cat Toy", ProdType.ACCESSORY, 5.99, store1);
        createAndSaveProduct("CL789", "Pet Shampoo", ProdType.CLEANING, 8.75, store1);

        createAndSaveProduct("FD234", "Cat Food", ProdType.FOOD, 9.25, store2);
        createAndSaveProduct("AC567", "Dog Collar", ProdType.ACCESSORY, 12.99, store2);
        createAndSaveProduct("CL890", "Litter Box Cleaner", ProdType.CLEANING, 7.50, store2);

        createAndSaveProduct("FD345", "Fish Food", ProdType.FOOD, 4.75, store3);
        createAndSaveProduct("AC678", "Bird Cage", ProdType.ACCESSORY, 29.99, store3);
        createAndSaveProduct("CL901", "Terrarium Cleaner", ProdType.CLEANING, 11.25, store3);

        // Créer 3 animaux par animalerie
        createAndSaveCat("Black", "123ABC", new Date(), store1);
        createAndSaveCat("White", "456DEF", new Date(), store1);
        createAndSaveCat("Orange", "789GHI", new Date(), store1);

        createAndSaveCat("Gray", "234BCD", new Date(), store2);
        createAndSaveCat("Brown", "567EFG", new Date(), store2);
        createAndSaveCat("Calico", "890HIJ", new Date(), store2);

        createAndSaveCat("Tabby", "345CDE", new Date(), store3);
        createAndSaveCat("Siamese", "678FGH", new Date(), store3);
        createAndSaveCat("Persian", "901IJK", new Date(), store3);
    }

    // Méthodes utilitaires pour créer et sauvegarder les objets
    private Address createAndSaveAddress(String number, String street, String zipCode, String city) {
        Address address = new Address();
        address.setNumber(number);
        address.setStreet(street);
        address.setZipCode(zipCode);
        address.setCity(city);
        return addressRepository.save(address);
    }

    private PetStore createAndSavePetStore(String name, String managerName, Address address) {
        PetStore store = new PetStore();
        store.setName(name);
        store.setManagerName(managerName);
        store.setAddress(address);
        return petStoreRepository.save(store);
    }

    private Product createAndSaveProduct(String code, String label, ProdType type, double price, PetStore store) {
        Product product = new Product();
        product.setCode(code);
        product.setLabel(label);
        product.setType(type);
        product.setPrice(price);

        product.setPetStore(store);

        if (product.getPetStores() == null) {
            product.setPetStores(new HashSet<>());
        }
        product.getPetStores().add(store);

        if (store.getProducts() == null) {
            store.setProducts(new HashSet<>());
        }
        store.getProducts().add(product);

        return productRepository.save(product);
    }

    private Cat createAndSaveCat(String couleur, String chipId, Date birth, PetStore store) {
        Cat cat = new Cat();
        cat.setCouleur(couleur);
        cat.setChipId(chipId);
        cat.setBirth(birth);
        cat.setPetStore(store);

        if (store.getAnimals() == null) {
            store.setAnimals(new HashSet<>());
        }
        store.getAnimals().add(cat);

        return (Cat) animalRepository.save(cat);
    }
}
