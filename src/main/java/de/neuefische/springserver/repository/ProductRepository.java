package de.neuefische.springserver.repository;

import de.neuefische.springserver.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<String, Product> productRepo = new HashMap<>();

    public List<Product> getProductList(){
        return List.copyOf(productRepo.values());
    }

    public Optional<Product> getProductById(String id){
        return Optional.ofNullable(productRepo.get(id));
    }

    public Product addProduct(Product product){
        productRepo.put(product.getId(), product);
        return product;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        for (Product product : productRepo.values()) {
            if(product.getName().toLowerCase().contains(name.toLowerCase())) {
                products.add(product);
            }
        }
        return products;
    }
}
