package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "m_category")
public class MenuCategory {
    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter     @Setter
    private String categoryId;

    @Column(name = "category_name", nullable = false, length = 150)
    @Getter     @Setter
    private String categoryName;
}
