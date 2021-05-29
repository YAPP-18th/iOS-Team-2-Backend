package com.yapp.yongyong.domain.user.entity;


import com.yapp.yongyong.global.domain.BaseTimeEntity;
import com.yapp.yongyong.global.error.NotExistException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name="users_authorities",
            joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "image_url")
    @Lob
    private String imageUrl;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "provider")
    private String provider;

    @Builder
    public User(String email, String password, Set<Authority> authorities, String nickname, String imageUrl, String introduction, String provider) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
        this.provider = provider;
    }

    public User update(String name, String picture){
        this.nickname = name;
        this.imageUrl = picture;

        return this;
    }

    public String getUserRoleKey(){
        return authorities.stream()
                .filter(authority -> authority.getAuthorityName().equals(Role.USER.getName()))
                .map(Authority::getAuthorityName)
                .findAny()
                .orElseThrow(()->new NotExistException("유저 권한이 존재하지 않습니다."));
    }
}
