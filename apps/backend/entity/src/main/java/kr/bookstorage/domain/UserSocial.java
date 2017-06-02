package kr.bookstorage.domain;

import kr.bookstorage.domain.code.SocialProvider;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 권성봉 on 2016. 12. 13..
 */
@Entity
@Data
@Table(name = "USER_SOCIAL")
public class UserSocial implements Serializable {

    @Id
    @OneToOne
    @NotNull
    @JoinColumn(name = "UNIQUE_ID")
    private User user;

    @NotNull
    @Column(name = "PROVIDER_ID")
    @Enumerated(EnumType.STRING)
    private SocialProvider providerId;

    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;

    @Column(name = "RANK")
    private int rank;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DISPLAY_NM")
    private String displayNm;

    @Column(name = "PROFILE_URL")
    private String profileUrl;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "SECRET")
    private String secret;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "EXPIRE_TIME")
    private long expireTime;

    private String displayName;

}
