package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@javax.persistence.Table (name = "m_table")
public class Table {
    @Id
    @Column(name = "table_id", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String tableId;
    @Column(name = "table_no", nullable = false)
    @Getter
    @Setter
    private String tableNo;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    @Getter     @Setter
    private TableStatus tableStatus;
}
