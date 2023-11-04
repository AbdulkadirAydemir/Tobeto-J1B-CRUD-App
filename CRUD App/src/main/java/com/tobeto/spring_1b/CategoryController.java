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
@RequestMapping("api/category")

public class CategoryController {

    List<Category> inMemoryList = new ArrayList<>();


    @GetMapping("{id}")
    public Category getById(@PathVariable int id){
        Category category = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst() // {}
                .orElseThrow();
        return category;
    }

    @PostMapping
    public Category post(@RequestBody Category category){
        inMemoryList.add(category);
        return category;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Optional<Category> existingCategory = inMemoryList.stream().filter(c -> c.getId() == id).findFirst();

        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setCategoryName(category.getCategoryName());
            return new ResponseEntity<>("Kategori güncellendi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bu ID'ye sahip kategori bulunamadı", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public Category deleteById(@PathVariable int id){
        Category category = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst() // {}
                .orElseThrow();
        return category;
    }
}
