package de.neuefische.springserver.repository;

import de.neuefische.springserver.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;

class ProductRepositoryTest {

    @Test
    void getProductList() {
        //GIVEN
        ProductRepository repository = new ProductRepository();
        repository.addProduct(new Product("Honey", "1"));
        repository.addProduct(new Product("Jam", "2"));
        repository.addProduct(new Product("Nutella", "3"));
        repository.addProduct(new Product("Banana", "4"));
        repository.addProduct(new Product("Apple", "5"));

        //WHEN
        List<Product> actual = repository.getProductList();

        //THEN
        assertThat(actual, containsInAnyOrder(
                new Product("Honey", "1"),
                new Product("Jam", "2"),
                new Product("Nutella", "3"),
                new Product("Banana", "4"),
                new Product("Apple", "5")));

    }

    @Test
    void getProductById() {
        //GIVEN
        ProductRepository repository = new ProductRepository();
        repository.addProduct(new Product("Honey", "1"));
        repository.addProduct(new Product("Jam", "2"));
        repository.addProduct(new Product("Nutella", "3"));
        repository.addProduct(new Product("Banana", "4"));
        repository.addProduct(new Product("Apple", "5"));

        //WHEN
        Optional<Product> actual = repository.getProductById("5");
        Optional<Product> empty = repository.getProductById("6");

        //THEN
        assertThat(actual, isPresentAndIs(new Product("Apple", "5")));
        assertThat(empty, isEmpty());
    }

    @Test
    void addProduct() {
        //GIVEN
        ProductRepository repository = new ProductRepository();

        //WHEN
        repository.addProduct(new Product("Honey", "1"));
        repository.addProduct(new Product("Jam", "2"));
        repository.addProduct(new Product("Nutella", "3"));
        repository.addProduct(new Product("Banana", "4"));
        repository.addProduct(new Product("Apple", "5"));

        //THEN
        assertThat(repository.getProductList(), containsInAnyOrder(
                new Product("Honey", "1"),
                new Product("Jam", "2"),
                new Product("Nutella", "3"),
                new Product("Banana", "4"),
                new Product("Apple", "5")));
    }
}