package com.kh.countingBell.domain;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @Column(name="food_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "foodSequence")
    @SequenceGenerator(name = "foodSequence", sequenceName = "SEQ_FOOD", allocationSize = 1)
    private int foodCode;

    @Column(name="food_type")
    private String foodType;


}
