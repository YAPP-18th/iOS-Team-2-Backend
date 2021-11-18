package com.yapp.post.entity;

import com.yapp.post.global.entity.BaseTimeEntity;
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

    @Column(name = "user_id")
    private Long userId;

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

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    Set<LikePost> likePosts = new HashSet<>();

    @Builder
    public Post(String content, Long userId, Place place, String reviewBadge) {
        this.content = content;
        this.userId = userId;
        this.place = place;
        this.reviewBadge = reviewBadge;
    }
}
