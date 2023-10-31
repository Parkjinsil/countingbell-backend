package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @Column(name = "reser_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reserSequence")
    @SequenceGenerator(name = "reserSequence", sequenceName = "SEQ_RESERVATION", allocationSize = 1)
    private int reserCode;

    @Column(name="reser_per")
    private Integer reserPer;

    @Column(name="reser_date")
    private Date reserDate;

    @Column(name="reser_time")
    private Date reserTime;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "res_code")
    private Restaurant restaurant;
    
}
