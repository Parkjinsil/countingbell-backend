package com.kh.countingBell.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class testDTO {
    private int menuCode;
    private int resCode;
    private MultipartFile menuPicture;
    private String menuName;

    private String menuPrice;

}
