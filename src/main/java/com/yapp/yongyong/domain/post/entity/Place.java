package com.yapp.yongyong.domain.post.entity;

import com.yapp.yongyong.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "places")
@Entity
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    @Lob
    private String location;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Builder
    public Place(String name, String location) {
        this.name = name;
        this.location = location;
        this.reviewCount = 0;
    }

    public void addReviewCount() {
        this.reviewCount++;
    }

    public void subReviewCount() {
        this.reviewCount--;
    }
}
