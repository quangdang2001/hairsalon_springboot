package com.project.hairsalon.service;

import com.project.hairsalon.model.BillDetail;
import com.project.hairsalon.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findByid(Long id);
    void save(Category category);
    void deleteById(Long id);
}
