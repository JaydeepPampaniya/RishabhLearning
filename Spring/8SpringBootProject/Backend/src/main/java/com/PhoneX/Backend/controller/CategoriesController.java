package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.entity.Categories;
import com.PhoneX.Backend.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService){
        this.categoriesService = categoriesService;
    }

    @PostMapping("/addCategories")
    public ResponseEntity<String> addCategories(@RequestBody Categories cat){
        return ResponseEntity.accepted().body(categoriesService.addCat(cat.getCategoriesName()));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getCategories(){
        return ResponseEntity.ok().body(categoriesService.getCategories());
    }
}
