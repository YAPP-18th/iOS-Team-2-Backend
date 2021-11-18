package com.yapp.post.repository;

import com.yapp.post.entity.PostContainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostContainerRepository extends JpaRepository<PostContainer, Long> {
}
