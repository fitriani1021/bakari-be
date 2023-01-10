package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Table;
import com.enigma.model.TableStatus;
import com.enigma.model.request.TableRequest;
import com.enigma.repository.TableRepository;
import com.enigma.repository.TableStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService{
    TableRepository tableRepository;
    TableStatusRepository tableStatusRepository;

    public TableServiceImpl(@Autowired TableRepository tableRepository, @Autowired TableStatusRepository tableStatusRepository) {
        this.tableRepository = tableRepository;
        this.tableStatusRepository = tableStatusRepository;
    }

    @Override
    public Table create(TableRequest tableRequest) throws Exception{
        try {
            Optional<TableStatus> tableStatus = tableStatusRepository.findById(tableRequest.getStatusId());
            if (tableStatus.isEmpty()){
                throw new NotFoundException("Table status is not found");
            }

            Table table = new Table();
            table.setTableNo(tableRequest.getTableNo());
            table.setTableStatus(tableStatus.get());
            return tableRepository.save(table);
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Table> tableList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Table> result = tableRepository.findAll(pageable);
        return result;
    }

    @Override
    public Table getById(String id) {
        Optional<Table> table = tableRepository.findById(id);
        if (table.isEmpty()){
            throw new NotFoundException("Table is not found");
        }
        return table.get();
    }

    @Override
    public void delete(String id) {
        try {
            Table existingTable = getById(id);
            tableRepository.delete(existingTable);
        }catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because Id is not found");
        }
    }
}
