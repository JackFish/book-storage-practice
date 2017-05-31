package kr.bookstorage.config;

import kr.bookstorage.domain.UserRole;
import kr.bookstorage.dto.UserDto;
import kr.bookstorage.user.impl.UserDAO;
import kr.bookstorage.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultUserConfig {

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public InitializingBean initializingBean(){
		return () -> {
			User adminUser = userDAO.findByEmail("admin@bookstorage.co.kr");
			if(ObjectUtils.isEmpty(adminUser)){
				UserDto.Create userCreate = new UserDto.Create();
				userCreate.setEmail("admin@bookstorage.co.kr");
				userCreate.setUserName("admin");
				userCreate.setPassword(passwordEncoder.encode("admin"));
				User user = modelMapper.map(userCreate, User.class);
				user.setEnabled(true);

				List<UserRole> userRoleList = new ArrayList<>();
				UserRole userRole = new UserRole();
				userRole.setRole("ROLE_ADMIN");
				userRole.setUser(user);
				userRoleList.add(userRole);
				user.setUserRoleList(userRoleList);

				userDAO.save(user);
			}

			User generalUser = userDAO.findByEmail("general@bookstorage.co.kr");
			if(ObjectUtils.isEmpty(generalUser)){
				UserDto.Create userCreate = new UserDto.Create();
				userCreate.setEmail("general@bookstorage.co.kr");
				userCreate.setUserName("general");
				userCreate.setPassword(passwordEncoder.encode("general"));
				User user = modelMapper.map(userCreate, User.class);
				user.setEnabled(true);

				List<UserRole> userRoleList = new ArrayList<>();
				UserRole userRole = new UserRole();
				userRole.setRole("ROLE_USER");
				userRole.setUser(user);
				userRoleList.add(userRole);
				user.setUserRoleList(userRoleList);

				userDAO.save(user);
			}
		};
	}
}
