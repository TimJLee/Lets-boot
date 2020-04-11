package com.timjlee.letsboot.springboot.service;

import com.timjlee.letsboot.springboot.domain.posts.Posts;
import com.timjlee.letsboot.springboot.domain.posts.PostsRepository;
import com.timjlee.letsboot.springboot.web.dto.PostsListResponseDto;
import com.timjlee.letsboot.springboot.web.dto.PostsResponseDto;
import com.timjlee.letsboot.springboot.web.dto.PostsSaveRequestDto;
import com.timjlee.letsboot.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 존재하지 않습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true) // 조회기능만 존재하는 서비스 메소드에 삽입, 조회 속도 증가
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


}
