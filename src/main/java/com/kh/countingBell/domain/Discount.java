package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Discount {

    @Id
    @Column(name="dis_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="discountSequence")
    @SequenceGenerator(name="discountSequence", sequenceName = "SEQ_DISCOUNT", allocationSize = 1)
    private int disCode;

    @Column(name="dis_desc")
    private String disDesc;

    @Column(name="dis_period")
    private String disPeriod;

    @ManyToOne
    @JoinColumn(name="res_code")
    private Restaurant restaurant;

}
