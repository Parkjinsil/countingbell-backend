package com.kh.countingBell.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String token;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private String gender;
    private int age;
    private String email;
    private String role;
}
