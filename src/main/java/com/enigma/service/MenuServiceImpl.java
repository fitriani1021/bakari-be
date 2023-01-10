package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Menu;
import com.enigma.model.MenuCategory;
import com.enigma.model.request.MenuRequest;
import com.enigma.repository.MenuCategoryRepository;
import com.enigma.repository.MenuRepository;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    RandomStringGenerator randomStringGenerator;
    MenuRepository menuRepository;
    MenuCategoryRepository menuCategoryRepository;

    public MenuServiceImpl(@Autowired RandomStringGenerator randomStringGenerator, @Autowired MenuRepository menuRepository, @Autowired MenuCategoryRepository menuCategoryRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.menuRepository = menuRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public Menu create(MenuRequest menuRequest) throws Exception{
        try {
            Optional<MenuCategory> menuCategory = menuCategoryRepository.findById(menuRequest.getCategoryId());
            if (menuCategory.isEmpty()){
                throw new NotFoundException("Menu category is not found");
            }

            Menu menu = new Menu();
            menu.setMenuId(randomStringGenerator.random());
            menu.setMenuName(menuRequest.getMenuName());
            menu.setPrice(menuRequest.getPrice());
            menu.setCategory(menuCategory.get());
            return menuRepository.save(menu);
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Menu> menuList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Menu> result = menuRepository.findAll(pageable);
        return result;
    }

    @Override
    public Menu getById(String id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isEmpty()){
            throw new NotFoundException("Menu not found");
        }
        return menu.get();
    }

    @Override
    public void delete(String id) {
        try {
            Menu existingMenu = getById(id);
            menuRepository.delete(existingMenu);
        }catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
