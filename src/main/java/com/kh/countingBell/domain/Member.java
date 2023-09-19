package com.kh.countingBell.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

public class Member {

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String nickname;

    @Column
    private String gender;

    @Column
    private int age;

    @Column
    private String email;

    @Column
    private String role;

}
