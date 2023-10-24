package com.kh.countingBell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private int resCode;
    private String resName;
    private String resAddr;
    private String resPhone;
    private String resOpenHour;
    private String resClose;
    private String resDesc;
    private Location location;
    private Food food;
    private MemberDTO memberDTO;
}



