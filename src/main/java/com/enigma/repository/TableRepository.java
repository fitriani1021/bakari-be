package com.enigma.repository;

import com.enigma.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, String > {
}
