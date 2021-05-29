package com.yapp.yongyong.domain.post.entity;

import com.yapp.yongyong.domain.user.entity.User;
import com.yapp.yongyong.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "like_count")
    private Integer likeCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "review_badge")
    private String reviewBadge;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    Set<PostContainer> postContainers = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    Set<PostImage> postImages = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    Set<Comment> comments = new HashSet<>();

    @Builder
    public Post(String content, User user, Place place, String reviewBadge) {
        this.content = content;
        this.user = user;
        this.place = place;
        this.reviewBadge = reviewBadge;
    }

    @PrePersist
    public void checkLikeCount(){
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
    }
}
