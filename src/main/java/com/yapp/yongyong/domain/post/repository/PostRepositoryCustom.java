package com.yapp.yongyong.domain.post.repository;

import com.yapp.yongyong.domain.post.entity.Post;
import com.yapp.yongyong.domain.user.entity.User;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getPostByMonth(User user, Integer month);
}
