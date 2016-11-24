package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class UserListMapper implements Func1<List<UserDTO>, List<User>> {
    @Override
    public List<User> call(List<UserDTO> userDTOs) {
        return Observable.from(userDTOs)
                .map(new Func1<UserDTO, User>() {
                    @Override
                    public User call(UserDTO userDTO) {
                        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
                    }
                })
                .toList()
                .toBlocking()
                .first();
    }
}
