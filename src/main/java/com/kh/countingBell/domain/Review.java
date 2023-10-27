package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Review {

    @Id
    @Column(name = "review_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reviewSequence")
    @SequenceGenerator(name = "reviewSequence", sequenceName = "SEQ_REVIEW", allocationSize = 1)
    private int reviewCode;

    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "review_grade")
    private int reviewGrade;

    @Column(name = "review_date")
    private Date reviewDate;

    @Column(name = "review_photo")
    private String reviewPhoto;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "res_code")
    private Restaurant restaurant;
}
