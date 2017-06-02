package kr.bookstorage.security.social.kakao.config.xml;

import kr.co.carlab.security.social.kakao.config.support.KakaoApiHelper;
import kr.co.carlab.security.social.kakao.connect.KakaoConnectionFactory;
import kr.co.carlab.security.social.kakao.security.KakaoAuthenticationService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.security.provider.SocialAuthenticationService;

import java.util.Map;

public class KakaoConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {
	public KakaoConfigBeanDefinitionParser() {
		super(KakaoConnectionFactory.class, KakaoApiHelper.class);
	}
	
	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return KakaoAuthenticationService.class;
	}
	
	@Override
	protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(KakaoConnectionFactory.class).addConstructorArgValue(appId);
		return builder.getBeanDefinition();
	}
}
