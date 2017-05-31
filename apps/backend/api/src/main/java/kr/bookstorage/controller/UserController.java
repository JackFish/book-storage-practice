package kr.bookstorage.controller;

import kr.bookstorage.domain.User;
import kr.bookstorage.dto.UserDto;
import kr.bookstorage.exception.ErrorStatus;
import kr.bookstorage.exception.exceptions.ForbiddenException;
import kr.bookstorage.security.service.CmmLoginHelper;
import kr.bookstorage.security.service.CmmUserDetails;
import kr.bookstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by ksb on 2017. 5. 29..
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto.Create user){
        userServiceImpl.create(user);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserDto.Update user, Authentication authentication){
        user.setUniqueId((UUID)authentication.getCredentials());
        userServiceImpl.update(user);
        // todo 세션에서 유저정보 수정해야함
    }

    @GetMapping("/exist/email/{email}")
    public void existEmail(@PathVariable("email") String email){
        if(userServiceImpl.existEmail(email)) throw new ForbiddenException(ErrorStatus.USER_EMAIL_EXIST_MESSAGE);
    }

    @GetMapping("/exist/name/{name}")
    public void existName(@PathVariable("name") String name){
        if(userServiceImpl.existUserName(name)) throw new ForbiddenException(ErrorStatus.USER_NAME_EXIST_MESSAGE);
    }

}
