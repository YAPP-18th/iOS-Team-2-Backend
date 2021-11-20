package com.yapp.yongyong.domain.user.entity;

import com.yapp.yongyong.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "block_users")
@Entity
public class BlockUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    private User to;

    public BlockUser(User from, User to) {
        this.from = from;
        this.to = to;
    }
}
