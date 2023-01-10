package com.enigma.controller;

import com.enigma.model.Menu;
import com.enigma.model.Table;
import com.enigma.model.request.TableRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.TableService;
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
@RequestMapping(UrlMapping.TABLE)
@Validated
public class TableController {
    ModelMapper modelMapper;
    TableService tableService;

    public TableController(@Autowired ModelMapper modelMapper, @Autowired TableService tableService) {
        this.modelMapper = modelMapper;
        this.tableService = tableService;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "ASC") String sortBy,
            @RequestParam(defaultValue = "tableNo") String direction
    ){
        Page<Table> tables = tableService.tableList(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get table list", tables));
    }

    @PostMapping
    public ResponseEntity createTable(@Valid @RequestBody TableRequest table) throws Exception{
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Table result = tableService.create(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create table", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        Table table = tableService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get table with id", table));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        tableService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete table", null));
    }
}
