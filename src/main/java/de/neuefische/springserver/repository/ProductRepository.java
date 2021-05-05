package de.neuefische.springserver.repository;

import de.neuefische.springserver.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
}
