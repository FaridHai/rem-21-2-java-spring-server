package de.neuefische.springserver.controller;

import de.neuefische.springserver.model.Product;
import de.neuefische.springserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductList(){
        return productService.getProductList();
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product){
        Optional<Product> optionalProduct = productService.addProduct(product);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
        }
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable String id, @Valid @RequestBody Product product){
        if (!id.equals(product.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid");
        }
        return productService.updateProduct(product);
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.getProductById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
        }
    }



}
