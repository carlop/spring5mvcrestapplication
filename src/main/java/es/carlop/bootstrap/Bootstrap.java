package es.carlop.bootstrap;

import es.carlop.domain.Category;
import es.carlop.domain.Customer;
import es.carlop.repositories.CategoryRepository;
import es.carlop.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category Data loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Jimmy");
        customer1.setLastName("McGill");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Charles");
        customer2.setLastName("McGill");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Kim");
        customer3.setLastName("Wexler");

        Customer customer4 = new Customer();
        customer4.setId(4L);
        customer4.setFirstName("Mike");
        customer4.setLastName("Ehrmantraut");

        Customer customer5 = new Customer();
        customer5.setId(5L);
        customer5.setFirstName("Nacho");
        customer5.setLastName("Varga");

        Customer customer6 = new Customer();
        customer6.setId(6L);
        customer6.setFirstName("Gus");
        customer6.setLastName("Fring");

        Customer customer7 = new Customer();
        customer7.setId(7L);
        customer7.setFirstName("Howard");
        customer7.setLastName("Hamlin");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
        customerRepository.save(customer6);
        customerRepository.save(customer7);

        System.out.println("Customer Data loaded = " + customerRepository.count());
    }

}
