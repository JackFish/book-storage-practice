package kr.bookstorage.security.social;

import kr.bookstorage.security.service.CmmSocialAndUserDetailService;
import kr.bookstorage.security.service.CustomSocialUsersConnectionRepository;
import kr.bookstorage.security.social.kakao.connect.KakaoConnectionFactory;
import kr.bookstorage.security.social.naver.connect.NaverConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.annotation.Resource;

/**
 * Created by 권성봉 on 2016. 8. 29..
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private ConnectionSignUp autoSignUpHandler;

    @Resource(name = "cmmUserDetailsService")
    private CmmSocialAndUserDetailService cmmUserDetailsService;

    @Value("${spring.social.kakao.app-id}")
    private String kakaoAppId;

    @Value("${spring.social.naver.app-id}")
    private String naverAppId;

    @Value("${spring.social.naver.app-secret}")
    private String naverSecret;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(
                new KakaoConnectionFactory(kakaoAppId)
        );
        connectionFactoryConfigurer.addConnectionFactory(
                new NaverConnectionFactory(
                        naverAppId,
                        naverSecret
                )
        );
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        CustomSocialUsersConnectionRepository usersConnectionRepository =
                new CustomSocialUsersConnectionRepository(cmmUserDetailsService, connectionFactoryLocator);

        usersConnectionRepository.setConnectionSignUp(autoSignUpHandler);

        return usersConnectionRepository;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }

}
