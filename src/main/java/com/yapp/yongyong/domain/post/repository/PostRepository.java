package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.domain.Place;
import com.yapp.yongyong.domain.post.domain.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "user"})
    List<Post> findAllByPlace(Place place);
}
