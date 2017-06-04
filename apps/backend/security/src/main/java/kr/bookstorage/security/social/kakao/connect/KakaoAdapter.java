package kr.bookstorage.security.social.kakao.connect;

import kr.bookstorage.security.social.kakao.api.Kakao;
import kr.bookstorage.security.social.kakao.api.KakaoProfile;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

public class KakaoAdapter implements ApiAdapter<Kakao> {
	@Deprecated
	public boolean test(Kakao api) {
		// do nothing
		return false;
	}
	
	public void setConnectionValues(Kakao kakao, ConnectionValues values) {
		KakaoProfile profile = kakao.userOperation().getUserProfile();
		values.setProviderUserId(Long.toString(profile.getId()));
		values.setDisplayName(profile.getProperties().getNickname());
		values.setProfileUrl("");
		values.setImageUrl(profile.getProperties().getProfile_image());
	}
	
	public UserProfile fetchUserProfile(Kakao kakao) {
		KakaoProfile profile = kakao.userOperation().getUserProfile();
		return new UserProfileBuilder().setName(profile.getProperties().getNickname()).setUsername(profile.getProperties().getNickname()).build();
	}
	
	@Deprecated
	public void updateStatus(Kakao kakao, String message) {
		// do nothing
	}
}
