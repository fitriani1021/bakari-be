package com.enigma.service;

import com.enigma.model.TableStatus;
import org.springframework.data.domain.Page;

public interface TableStatusService {
    TableStatus create(TableStatus tableStatus);
    Page<TableStatus> statusList(Integer page, Integer size, String direction, String sortBy);
    TableStatus getById(String id);
    void delete(String id);
}
