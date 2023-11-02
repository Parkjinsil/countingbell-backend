package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
public class Pick {

    @Id
    @Column(name="pick_code")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="pickSequence")
    @SequenceGenerator(name="pickSequence", sequenceName="SEQ_PICK", allocationSize=1)
    private int pickCode;

    @ManyToOne
    @JoinColumn(name="res_code")
    private Restaurant restaurant;

    @Column(name="pick_time")
    private Date pickTime;

    @ManyToOne
    @JoinColumn(name="id")
    private Member member;

}
