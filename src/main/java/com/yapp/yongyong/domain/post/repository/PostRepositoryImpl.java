package com.yapp.yongyong.domain.post.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yapp.yongyong.domain.post.entity.*;
import com.yapp.yongyong.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.yapp.yongyong.domain.post.entity.QComment.comment;
import static com.yapp.yongyong.domain.post.entity.QLikePost.likePost;
import static com.yapp.yongyong.domain.post.entity.QPlace.place;
import static com.yapp.yongyong.domain.post.entity.QPost.post;
import static com.yapp.yongyong.domain.post.entity.QPostContainer.postContainer;
import static com.yapp.yongyong.domain.post.entity.QPostImage.postImage;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getPostByMonth(User user, Integer month) {
        return queryFactory.selectFrom(post)
                .leftJoin(post.postContainers, postContainer)
                .fetchJoin()
                .leftJoin(post.postImages, postImage)
                .fetchJoin()
                .leftJoin(post.comments, comment)
                .fetchJoin()
                .leftJoin(post.place, place)
                .fetchJoin()
                .leftJoin(post.likePosts, likePost)
                .where(monthEq(month), post.user.eq(user))
                .distinct()
                .fetch();
    }

    private Predicate monthEq(Integer month) {
        return month != null ?
                post.createdDate.month().eq(month) : post.createdDate.month().eq(LocalDate.now().getMonth().getValue());
    }
}
