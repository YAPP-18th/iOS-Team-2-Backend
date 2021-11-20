package com.yapp.post.repository;

import com.yapp.post.entity.Place;
import com.yapp.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "place", "likePosts"})
    List<Post> findAllByPlace(Place place);

    @EntityGraph(attributePaths = {"postContainers", "postImages", "comments", "place", "likePosts"})
    List<Post> findAllByUserId(Long userId);
}
