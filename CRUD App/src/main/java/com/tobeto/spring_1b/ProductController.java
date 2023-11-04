package com.tobeto.spring_1b;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {

    List<Product> inMemoryList = new ArrayList<>();

    @GetMapping("{id}")
    public Product getById(@PathVariable int id){
        Product product = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst() // {}
                .orElseThrow();
        return product;
    }

    @PostMapping
    public Product post(@RequestBody Product product){
        inMemoryList.add(product);
        return product;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Optional<Object> inMemoryProductList;
        Optional<Product> existingProduct = inMemoryList.stream().filter(p -> p.getId() == id).findFirst();

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setProductName(product.getProductName());
            return new ResponseEntity<>("Ürün güncellendi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bu ID'ye sahip ürün bulunamadı", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public Product deleteById(@PathVariable int id){
        Product product = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst() // {}
                .orElseThrow();
        return product;
    }
}
