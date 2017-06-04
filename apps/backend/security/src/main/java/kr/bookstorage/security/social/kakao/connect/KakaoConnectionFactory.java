package kr.bookstorage.security.social.kakao.connect;

import kr.bookstorage.security.social.kakao.api.Kakao;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class KakaoConnectionFactory extends OAuth2ConnectionFactory<Kakao> {
	public KakaoConnectionFactory(String clientId) {
		super("kakao", new KakaoServiceProvider(clientId), new KakaoAdapter());
	}
}
