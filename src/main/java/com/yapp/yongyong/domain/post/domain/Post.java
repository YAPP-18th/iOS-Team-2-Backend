package com.yapp.yongyong.domain.post.domain;

import com.yapp.yongyong.domain.user.domain.User;
import com.yapp.yongyong.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<PostContainer> postContainers = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<PostImage> postImages = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
