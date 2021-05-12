package com.yapp.yongyong.domain.post.domain;

import com.yapp.yongyong.global.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "containers")
@Entity
public class Container extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cotainer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;
}
