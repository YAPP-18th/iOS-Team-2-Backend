package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId);
}
