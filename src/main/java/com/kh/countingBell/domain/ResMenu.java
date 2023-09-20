package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "res_menu")
public class ResMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "resMenuSequence")
    @SequenceGenerator(name = "resMenuSequence", sequenceName = "SEQ_RES_MENU", allocationSize = 1)
    private int resMenuCode;


    @ManyToOne
    @JoinColumn(name = "res_code")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_code")
    private Menu menu;

}
