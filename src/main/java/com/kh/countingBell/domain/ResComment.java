package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name="RES_COMMENT")

public class ResComment {

    @Id
    @Column(name="res_comment_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ResCommentSequence")
    @SequenceGenerator(name="ResCommentSequence", sequenceName = "SEQ_RES_COMMENT", allocationSize = 1)
    private int resCommentCode;

    @Column(name="res_comment_detail")
    private String resCommentDetail;

    @Column(name="res_comment_date")
    private Date resCommentDate;

    @Column(name="res_parent_comment")
    private String resParentComment;

    @ManyToOne
    @JoinColumn(name="id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="review_code")
    private Review review;

}
