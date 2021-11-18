package com.yapp.post.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "container_name")
    private String containerName;

    @Column(name = "container_size")
    private String containerSize;

    @Column(name = "container_count")
    private Integer containerCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public PostContainer(String food, Integer foodCount, String containerName, String containerSize, Integer containerCount, Post post) {
        this.food = food;
        this.foodCount = foodCount;
        this.containerName = containerName;
        this.containerSize = containerSize;
        this.containerCount = containerCount;
        this.post = post;
    }
}
