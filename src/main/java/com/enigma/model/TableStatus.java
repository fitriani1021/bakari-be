package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "table_status")
public class TableStatus {
    @Id
    @Column(name = "status_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String statusId;
    @Column(name = "status_name", nullable = false)
    @Getter
    @Setter
    private String statusName;
}
