package com.hwan.springclient.exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {

    USD("미국"),
    KRW("한국"),
    JPY("일본"),
    PHP("필리핀"),
    ;

    private final String nation;

}
