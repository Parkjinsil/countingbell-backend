package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @Column(name="menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "menuSequence")
    @SequenceGenerator(name = "menuSequence", sequenceName = "SEQ_MENU", allocationSize = 1)
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private String menuPrice;

    @Column(name="menu_picture")
    private String menuPicture;

    @ManyToMany(mappedBy = "menus")
    private List<Restaurant> restaurants;

}
