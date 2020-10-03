package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findOneUser(String userName);

    Optional<User> findOneUser(Long userID);

    Optional<UserInfo> findOneUserInfo(String userName);

    List<User> findAllExceptMe(String myUserName);

    List<UserInfo> findAllExceptMeInfo(String myUserName);

    List<User> findAllUsers();

    List<UserInfo> findAllUsersInfo();
}
