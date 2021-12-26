package com.project.hairsalon.service.iplm;

import com.project.hairsalon.model.Bill;
import com.project.hairsalon.model.Category;
import com.project.hairsalon.repo.CategoryRepo;
import com.project.hairsalon.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService implements ICategoryService {
    private final CategoryRepo categoryRepo;
    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findByid(Long id) {
        Optional<Category> result = categoryRepo.findById(id);
        Category category = null;
        if (result.isPresent()) {
            category=result.get();
        }
        else {
            return null;
        }
        return category;
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}
