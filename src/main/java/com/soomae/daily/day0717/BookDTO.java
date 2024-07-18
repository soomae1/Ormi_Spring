package com.soomae.daily.day0717;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title; // 제목
    private String author; // 저자
    private String isbn; // 도서 번호
    private int price; // 가격
    private int publishedYear; // 출판연도
}
