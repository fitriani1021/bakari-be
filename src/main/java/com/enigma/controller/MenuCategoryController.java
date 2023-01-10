package com.enigma.controller;

import com.enigma.model.MenuCategory;
import com.enigma.model.request.MenuCategoryRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.MenuCategoryService;
import com.enigma.util.UrlMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlMapping.MENU_CATEGORY)
public class MenuCategoryController {
    MenuCategoryService menuCategoryService;
    @Autowired
    private ModelMapper modelMapper;

    public MenuCategoryController(@Autowired MenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "ASC") String sortBy,
            @RequestParam(defaultValue = "categoryName") String direction
    ) {
        Page<MenuCategory> result = menuCategoryService.categoryList(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all menu category", result));
    }

    @PostMapping
    ResponseEntity createCategory(@Valid @RequestBody MenuCategoryRequest categoryRequest){
        MenuCategory menuCategory = modelMapper.map(categoryRequest, MenuCategory.class);
        MenuCategory result = menuCategoryService.create(menuCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create menu category", result));
    }
}
