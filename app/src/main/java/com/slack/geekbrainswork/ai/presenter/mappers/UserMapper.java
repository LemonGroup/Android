package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import rx.functions.Func1;

public class UserMapper implements Func1<UserDTO,User> {
    @Override
    public User call(UserDTO userDTO) {
<<<<<<< HEAD
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
=======
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPrivilege());
>>>>>>> origin/master
    }
}
