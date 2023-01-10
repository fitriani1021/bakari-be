package com.enigma.service;

import com.enigma.model.MenuCategory;
import org.springframework.data.domain.Page;

public interface MenuCategoryService {
    MenuCategory create(MenuCategory menuCategory);
    Page<MenuCategory> categoryList(Integer page, Integer size, String direction, String sortBy);
    MenuCategory getById(String id) ;
    void delete(String id);
}
