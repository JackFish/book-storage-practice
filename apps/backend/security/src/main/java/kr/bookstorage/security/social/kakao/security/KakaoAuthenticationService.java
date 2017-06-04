package kr.bookstorage.security.social.kakao.security;

import kr.bookstorage.security.social.kakao.api.Kakao;
import kr.bookstorage.security.social.kakao.connect.KakaoConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class KakaoAuthenticationService extends OAuth2AuthenticationService<Kakao> {
	public KakaoAuthenticationService(String clientId) {
		super(new KakaoConnectionFactory(clientId));
	}
}
