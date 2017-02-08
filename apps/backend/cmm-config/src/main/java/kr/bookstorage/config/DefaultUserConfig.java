package kr.bookstorage.config;

import kr.bookstorage.dto.UserDto;
import kr.bookstorage.user.impl.UserDAO;
import kr.bookstorage.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Configuration
public class DefaultUserConfig {

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Autowired
	private ModelMapper modelMapper;

	@Bean
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public InitializingBean initializingBean(){
		return () -> {
			User defaultUser = userDAO.findByEmail("default@bookstorage.co.kr");
			if(ObjectUtils.isEmpty(defaultUser)){
				UserDto.Create userCreate = new UserDto.Create();
				userCreate.setEmail("default@bookstorage.co.kr");
				userCreate.setUserName("Default");
				userCreate.setPassword("Default");

				User user = modelMapper.map(userCreate, User.class);
				user.setEnabled(false);

				defaultUser = userDAO.save(user);
			}

			System.setProperty("defaultUserUniqueId", defaultUser.getUniqueId().toString());
		};
	}
}
