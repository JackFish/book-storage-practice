package kr.bookstorage.security.social.naver.api;

import kr.bookstorage.security.social.naver.api.abstracts.UserOperation;
import org.springframework.social.ApiBinding;

public interface Naver extends ApiBinding {
	UserOperation userOperation();
}
