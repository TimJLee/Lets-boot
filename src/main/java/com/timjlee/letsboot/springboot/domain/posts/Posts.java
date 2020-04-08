package com.timjlee.letsboot.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 문자열의 경우 VARCHAR(255)가 기본값이므로 사이즈를 500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false) // 타입을 TEXT 로 변환
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author =author;
    }
}