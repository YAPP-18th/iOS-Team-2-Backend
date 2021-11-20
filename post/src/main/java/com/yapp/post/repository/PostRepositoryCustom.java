package com.yapp.post.repository;


import com.yapp.post.entity.Post;
import java.util.List;


public interface PostRepositoryCustom {

    List<Post> getPostByMonth(Long userId, Integer month);
}
