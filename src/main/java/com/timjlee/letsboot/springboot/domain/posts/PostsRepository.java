package com.timjlee.letsboot.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { //<Entitiy 클래스, PK 타입>

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // SpringDataJpa 에서 제공하지 않는 메소드는 이렇게 쿼리로 작성하면 된다.
    List<Posts> findAllDesc();
}
