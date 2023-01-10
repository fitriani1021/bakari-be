package com.enigma.controller;

import com.enigma.model.Menu;
import com.enigma.model.request.MenuRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.MenuService;
import com.enigma.util.UrlMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlMapping.MENU)
@Validated
public class MenuController {
    ModelMapper modelMapper;
    MenuService menuService;

    public MenuController(@Autowired ModelMapper modelMapper, @Autowired MenuService menuService) {
        this.modelMapper = modelMapper;
        this.menuService = menuService;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "ASC") String sortBy,
            @RequestParam(defaultValue = "menuName") String direction
    ){
        Page<Menu> menus = menuService.menuList(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get menu list", menus));
    }

    @PostMapping
    public ResponseEntity createMenu(@Valid @RequestBody MenuRequest menu) throws Exception{
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Menu result = menuService.create(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create menu", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        Menu menu = menuService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get menu with id", menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        menuService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete menu", null));
    }
}
