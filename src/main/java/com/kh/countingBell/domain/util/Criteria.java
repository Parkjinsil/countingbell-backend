package com.kh.countingBell.domain.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Criteria {

    private int page; // 페이지 번호
    private int amount; // 한 페이지 당 몇 개의 데이터를 보여줄지

    public Criteria() {
        this(1, 15); // 기본값 지정해서 처리
    }

    public Criteria(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }
}
