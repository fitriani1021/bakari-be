package com.enigma.service;

import com.enigma.model.Menu;
import com.enigma.model.request.MenuRequest;
import org.springframework.data.domain.Page;

public interface MenuService {
    Menu create(MenuRequest menuRequest) throws Exception;
    Page<Menu> menuList(Integer page, Integer size, String direction, String sortBy);
    Menu getById(String id) ;
    void delete(String id) ;
}
