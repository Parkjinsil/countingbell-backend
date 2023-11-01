package com.kh.countingBell.domain.util;

import com.kh.countingBell.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private long totalCount; // 전체 글 수
    private int totalPages; // 총 페이지 개수
    private int getNumber; // 현재 페이지 번호
    private boolean hasNext; // 다음 페이지 존재 여부
    private boolean hasPrev; // 이전 페이지 존재 여부
    private boolean isFirst; // 시작 페이지 여부


    private List<Restaurant> restaurantList;
}