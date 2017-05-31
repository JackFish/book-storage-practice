package kr.bookstorage.service;

import kr.bookstorage.domain.User;
import kr.bookstorage.dto.UserDto;

/**
 * Created by ksb on 2017. 5. 29..
 */
public interface UserService {
    void create(UserDto.Create user);

    User update(UserDto.Update user);

    boolean existEmail(String email);

    boolean existName(String name);
}