package com.yapp.yongyong.domain.user.domain;

import com.yapp.yongyong.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "terms_of_service")
@Entity
public class TermsOfService extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_of_service_id")
    private Long id;

    @Column
    private Boolean location;

    @Column
    private Boolean service;

    @Column
    private Boolean privacy;

    @Column
    private Boolean marketing;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TermsOfService(Boolean location, Boolean service, Boolean privacy, Boolean marketing, User user) {
        this.location = location;
        this.service = service;
        this.privacy = privacy;
        this.marketing = marketing;
        this.user = user;
    }
}
