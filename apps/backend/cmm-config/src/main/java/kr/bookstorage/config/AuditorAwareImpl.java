package kr.bookstorage.config;

import kr.bookstorage.domain.User;
import kr.bookstorage.security.service.CmmLoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by 권 오빈 on 2016. 7. 22..
 */
//@Profile("!local")
@Component
@Slf4j
public class AuditorAwareImpl implements AuditorAware<User> {

	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public User getCurrentAuditor() {
		if(CmmLoginHelper.getUser() != null){
			return modelMapper.map(CmmLoginHelper.getUser(), User.class);
		}
		return null;
	}

}
