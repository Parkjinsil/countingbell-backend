package com.kh.countingBell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class Restaurant {

    @Id
    @Column(name="res_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "restaurantSequence")
    @SequenceGenerator(name = "restaurantSequence", sequenceName = "SEQ_RESTAURANT", allocationSize = 1)
    private int resCode;

    @Column(name="res_name")
    private String resName;

    @Column(name="res_addr")
    private String resAddr;

    @Column(name="res_phone")
    private String resPhone;

    @Column(name="res_open_hour")
    private String resOpenHour;

    @Column(name="res_close")
    private String resClose;

    @Column(name="res_desc")
    private String resDesc;




    @ManyToOne
    @JoinColumn(name="local_code" )
    private Location location;

    @ManyToOne
    @JoinColumn(name="food_code")
    private Food food;


    @ManyToOne
    @JoinColumn(name="id")
    private Member member;




}
