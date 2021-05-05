package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    final ProductRepository productRepositoryMock = mock(ProductRepository.class);

    @Test
    void getProductList() {
        ArrayList<Product> expected = new ArrayList<>(List.of(new Product("Honey", "1"), new Product("Watermelon", "5")));
        when(productRepositoryMock.getProductList()).thenReturn(expected);

        ProductService productService = new ProductService(productRepositoryMock);

        List<Product> actual = productService.getProductList();

        verify(productRepositoryMock).getProductList();
        assertThat(actual, containsInAnyOrder(new Product("Honey", "1"), new Product("Watermelon", "5")));
    }

    @Test
    void getProductByIdValidProduct() {
        Product honey = new Product("Honey", "1");
        Product watermelon = new Product("Watermelon", "5");
        when(productRepositoryMock.getProductById("5")).thenReturn(java.util.Optional.of(watermelon));

        ProductService productService = new ProductService(productRepositoryMock);

        Optional<Product> actual = productService.getProductById("5");

        verify(productRepositoryMock).getProductById("5");
        assertThat(actual, isPresentAndIs(watermelon));
    }

    @Test
    void getProductByIdNoProduct() {
        Product honey = new Product("Honey", "1");
        Product watermelon = new Product("Watermelon", "5");
        when(productRepositoryMock.getProductById("3")).thenReturn(java.util.Optional.empty());

        ProductService productService = new ProductService(productRepositoryMock);

        Optional<Product> actual = productService.getProductById("3");

        verify(productRepositoryMock).getProductById("3");
        assertThat(actual, isEmpty());
    }

    @Test
    void addNewProduct() {
        Product newProduct = new Product("Apple", "3");
        when(productRepositoryMock.addProduct(newProduct)).thenReturn(newProduct);

        ProductService productService = new ProductService(productRepositoryMock);

        Optional<Product> actual = productService.addProduct(newProduct);

        verify(productRepositoryMock).addProduct(newProduct);

        assertThat(actual, isPresentAndIs(newProduct));
    }

    @Test
    void addExistingProduct() {
        Product newProduct = new Product("Apple", "3");
        when(productRepositoryMock.getProductById("3")).thenReturn(Optional.of(newProduct));

        ProductService productService = new ProductService(productRepositoryMock);

        Optional<Product> actual = productService.addProduct(newProduct);

        verify(productRepositoryMock, times(0)).addProduct(newProduct);
        assertThat(actual, isEmpty());
    }

    @Test
    void updateProduct() {
        Product newProduct = new Product("Apple", "3");
        when(productRepositoryMock.addProduct(newProduct)).thenReturn(newProduct);

        ProductService productService = new ProductService(productRepositoryMock);

        Product actual = productService.updateProduct(newProduct);

        verify(productRepositoryMock).addProduct(newProduct);
        assertThat(actual, equalTo(newProduct));
    }
}