package de.neuefische.springserver.service;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList() {
        return this.productRepository.getProductList();
    }

    public Optional<Product> getProductById(String id) {
        return this.productRepository.getProductById(id);
    }

    public Optional<Product> addProduct(Product product) {
        Optional<Product> optionalProduct = getProductById(product.getId());
        if (optionalProduct.isPresent()) {
            return Optional.empty();
        }
        else {
            return Optional.of(this.productRepository.addProduct(product));
        }
    }

    public Product updateProduct(Product product) {
        return this.productRepository.addProduct(product);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.getProductsByName(name);
    }
}
