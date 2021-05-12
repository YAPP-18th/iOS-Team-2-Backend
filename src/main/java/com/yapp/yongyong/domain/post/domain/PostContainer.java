package com.yapp.yongyong.domain.post.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts_containers")
@Entity
public class PostContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food")
    private String food;

    @Column(name = "food_count")
    private Integer foodCount;

    @OneToOne
    @JoinColumn(name = "container_id")
    private Container container;

    @Column(name = "container_count")
    private Integer containerCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        post.getPostContainers().add(this);
    }

    @Builder
    public PostContainer(String food, Integer foodCount, Container container, Integer containerCount) {
        this.food = food;
        this.foodCount = foodCount;
        this.container = container;
        this.containerCount = containerCount;
    }
}
