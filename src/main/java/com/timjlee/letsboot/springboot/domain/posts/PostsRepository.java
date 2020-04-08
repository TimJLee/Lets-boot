package com.timjlee.letsboot.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { //<Entitiy 클래스, PK 타입>
}
