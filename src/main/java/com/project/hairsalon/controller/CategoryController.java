package com.project.hairsalon.controller;

import com.project.hairsalon.DTO.MessageDTO;
import com.project.hairsalon.model.Agency;
import com.project.hairsalon.model.Category;
import com.project.hairsalon.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/categories")
    public List<Category> getAllAgency(){
        return categoryService.findAll();
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable Long id){
        Category category = categoryService.findByid(id);

        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @PostMapping("/categories")
    public Category addAgency(@RequestBody Category category){
        category.setId(null);
        categoryService.save(category);
        return category;
    }
    @PutMapping("/categories")
    public Category updateAgency(@RequestBody Category category){
        categoryService.save(category);
        return category;
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable Long id){
        categoryService.deleteById(id);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setError_message("Deleted Category id= "+id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }
}
