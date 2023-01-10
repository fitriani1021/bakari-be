package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.model.MenuCategory;
import com.enigma.repository.MenuCategoryRepository;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService{
    RandomStringGenerator randomStringGenerator;
    MenuCategoryRepository menuCategoryRepository;

    public MenuCategoryServiceImpl(@Autowired RandomStringGenerator randomStringGenerator, @Autowired MenuCategoryRepository menuCategoryRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public MenuCategory create(MenuCategory menuCategory) {
        try {
            menuCategory.setCategoryId(randomStringGenerator.random());
            MenuCategory newCategory = menuCategoryRepository.save(menuCategory);
            return newCategory;
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public Page<MenuCategory> categoryList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<MenuCategory> categories = menuCategoryRepository.findAll(pageable);
        return categories;
    }

    @Override
    public MenuCategory getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
