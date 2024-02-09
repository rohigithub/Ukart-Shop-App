package com.pro.ukart.Controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pro.ukart.Entities.Category;
import com.pro.ukart.Repositories.CategoryRepo;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/createCat")
    public String addCategory(@RequestBody Category category){
        categoryRepo.save(category);
        return "Category created";
    }

    
    @GetMapping("/getAllCat")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> data = categoryRepo.findAll();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/deleteCategory")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryRepo.deleteAll();
        return "All Categories Deleted";
    }








    
}
