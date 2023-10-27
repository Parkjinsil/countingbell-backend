package com.kh.countingBell.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile resPicture;
    private int resPicks;

    private int localCode;
    private int foodCode;
    private String id;


}
