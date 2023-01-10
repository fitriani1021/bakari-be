package com.enigma.service;

import com.enigma.model.Table;
import com.enigma.model.request.TableRequest;
import org.springframework.data.domain.Page;

public interface TableService {
    Table create(TableRequest tableRequest) throws Exception;
    Page<Table> tableList(Integer page, Integer size, String direction, String sortBy);
    Table getById(String id);
    void delete(String id);
}
