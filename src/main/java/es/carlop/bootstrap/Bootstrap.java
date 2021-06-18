package es.carlop.bootstrap;

import es.carlop.domain.Category;
import es.carlop.domain.Customer;
import es.carlop.domain.Vendor;
import es.carlop.repositories.CategoryRepository;
import es.carlop.repositories.CustomerRepository;
import es.carlop.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

        loadVendors();
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

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Western Tasty Fruits Ltd.");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Exotic Fruits Company");

        Vendor vendor3 = new Vendor();
        vendor3.setId(3L);
        vendor3.setName("Home Fruits");

        Vendor vendor4 = new Vendor();
        vendor4.setId(4L);
        vendor4.setName("Fun Fresh Fruits Ltd.");

        Vendor vendor5 = new Vendor();
        vendor5.setId(5L);
        vendor5.setName("Nuts for Nuts Company");

        Vendor vendor6 = new Vendor();
        vendor6.setId(6L);
        vendor6.setName("Franks Fresh Fruits from France Ltd.");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);
        vendorRepository.save(vendor6);

        System.out.println("Vendor Data loaded = " + vendorRepository.count());
    }

}
