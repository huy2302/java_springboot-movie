package com.example.movie3layer.mapper;

import com.example.movie3layer.dto.UserDTO;
import com.example.movie3layer.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserToUserDTO implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUserID(),
                user.getUsername(),
                user.getPhone_number(),
                user.getRole(),
                user.getAvatar()
        );
    }
}
