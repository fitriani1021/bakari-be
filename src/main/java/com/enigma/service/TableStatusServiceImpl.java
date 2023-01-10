package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.model.TableStatus;
import com.enigma.repository.MenuCategoryRepository;
import com.enigma.repository.TableStatusRepository;
import com.enigma.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TableStatusServiceImpl implements TableStatusService{
    RandomStringGenerator randomStringGenerator;
    TableStatusRepository tableStatusRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    public TableStatusServiceImpl(@Autowired RandomStringGenerator randomStringGenerator, @Autowired TableStatusRepository tableStatusRepository,
                                  MenuCategoryRepository menuCategoryRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.tableStatusRepository = tableStatusRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public TableStatus create(TableStatus tableStatus) {
        try {
            tableStatus.setStatusId(randomStringGenerator.random());
            TableStatus newStatus = tableStatusRepository.save(tableStatus);
            return newStatus;
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public Page<TableStatus> statusList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<TableStatus> statuses = tableStatusRepository.findAll(pageable);
        return statuses;
    }

    @Override
    public TableStatus getById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
