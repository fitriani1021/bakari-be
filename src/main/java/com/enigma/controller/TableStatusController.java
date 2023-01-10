package com.enigma.controller;

import com.enigma.model.TableStatus;
import com.enigma.model.request.TableStatusRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.TableStatusService;
import com.enigma.util.UrlMapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlMapping.TABLE_STATUS)
public class TableStatusController {
    TableStatusService tableStatusService;
    ModelMapper modelMapper;

    public TableStatusController(@Autowired TableStatusService tableStatusService, @Autowired ModelMapper modelMapper) {
        this.tableStatusService = tableStatusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "ASC") String sortBy,
            @RequestParam(defaultValue = "statusName") String direction
    ) {
        Page<TableStatus> result = tableStatusService.statusList(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get all table status", result));
    }

    @PostMapping
    ResponseEntity createStatus(@Valid @RequestBody TableStatusRequest statusRequest) {
        TableStatus tableStatus = modelMapper.map(statusRequest, TableStatus.class);
        TableStatus result = tableStatusService.create(tableStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success create table status", result));
    }
}
