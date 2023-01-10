package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "m_menu")
public class Menu {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "menu_id", nullable = false)
    @Getter     @Setter
    private String menuId;

    @Column(name = "menu_name", nullable = false, length = 150, unique = true)
    @Getter     @Setter
    private String menuName;

    @Column(name = "price", nullable = false)
    @Getter     @Setter
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    @Getter     @Setter
    private MenuCategory category;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
