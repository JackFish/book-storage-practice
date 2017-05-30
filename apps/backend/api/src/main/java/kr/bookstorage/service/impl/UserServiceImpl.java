package kr.bookstorage.service.impl;

import kr.bookstorage.domain.User;
import kr.bookstorage.domain.UserRole;
import kr.bookstorage.dto.UserDto;
import kr.bookstorage.repository.UserRepository;
import kr.bookstorage.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksb on 2017. 5. 29..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void create(UserDto.Create user) {
        User result = modelMapper.map(user, User.class);

        List<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(result);
        userRoleList.add(userRole);
        result.setUserRoleList(userRoleList);
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        result.setEnabled(true);

        userRepository.save(result);
    }

    @Transactional
    @Override
    public void update(UserDto.Update user) {
        User result = userRepository.findOne(user.getUniqueId());

        if(!ObjectUtils.isEmpty(user.getName())){
            result.setName(user.getName());
        }
        if(!ObjectUtils.isEmpty(user.getPassword())){
            result.setPassword(passwordEncoder.encode(result.getPassword()));
        }
    }

    @Override
    public boolean existEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(!ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }

    @Override
    public boolean existName(String name) {
        User user = userRepository.findByName(name);

        if(!ObjectUtils.isEmpty(user)){
            return true;
        }
        return false;
    }
}
