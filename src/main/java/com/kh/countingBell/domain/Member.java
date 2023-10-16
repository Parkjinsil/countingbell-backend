package com.kh.countingBell.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
