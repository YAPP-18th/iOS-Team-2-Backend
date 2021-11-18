package com.yapp.post.entity;

import com.yapp.post.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content, Long userId, Post post) {
        this.content = content;
        this.userId = userId;
        this.post = post;
    }

    public void update(String content) {
        this.content = content;
    }
}
