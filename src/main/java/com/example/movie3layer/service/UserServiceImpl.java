package com.example.movie3layer.service;

import com.example.movie3layer.dto.UserAdd;
import com.example.movie3layer.dto.UserDTO;
import com.example.movie3layer.dto.UserShortDTO;
import com.example.movie3layer.exception.NotFoundException;
import com.example.movie3layer.mapper.UserToUserDTO;
import com.example.movie3layer.model.User;
import com.example.movie3layer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserToUserDTO userToUserDTO;

    @Override
    public UserDTO getUsernameAndPassword(String uname, String password, String role) {
        Optional<User> user = userRepository.findUserByUsernamePass(uname, password, role);

        if (user.isPresent()) {
            User user1 = userRepository.getUserByUnamePass(uname, password, role);

            return userToUserDTO.apply(user1);
        }

        throw new NotFoundException("Tài khoản sai thông tin đăng nhập");
    }

    @Override
    public Boolean checkExistUname(String uname) {
        Optional<User> user = userRepository.findUserByUsername(uname);

        if (user.isPresent()) {
            return true;
        }

        return false;
    }

    @Override
    public User addUser(String uname, String pass) {
        if (!checkExistUname(uname)) {
            User user = new User(uname, pass, "1");
            userRepository.save(user);
            return user;
        }
        throw new NotFoundException("Tài khoản đã tồn tại");
    }

    @Override
    public UserShortDTO getUserByUserID(int id) {
        User user = userRepository.getUserByUserID(id);

        return new UserShortDTO(
                user.getUserID(),
                user.getUsername(),
                user.getAvatar()
        ) ;
    }

    @Override
    public UserDTO getUserByID(int id) {
        User user = userRepository.getUserByUserID(id);

        return new UserDTO(
                user.getUserID(),
                user.getUsername(),
                user.getPhone_number(),
                user.getRole(),
                user.getAvatar()
        );
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userToUserDTO::apply).collect(Collectors.toList());
    }

    @Override
    public void editUser(UserDTO userDTO) {
        userRepository.updateUser(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getPhone_number(),
                userDTO.getRole(),
                userDTO.getAvatar()
        );
    }

    @Override
    public void addUserAdmin(UserAdd userAdd) {
        User user = new User(
                userAdd.getId(),
                userAdd.getName(),
                userAdd.getPassword(),
                userAdd.getPhone_number(),
                userAdd.getRole(),
                userAdd.getAvatar()
        );
        userRepository.save(user);
    }

    @Override
    public Boolean changePassword(int userId, String password, String new_password) {
        try {
            Optional<User> user = userRepository.findUserByIDPass(userId, password, "1");

            if (user.isPresent()) {
                userRepository.changePassword(userId, new_password);
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void removeUser(int id) {
        try {
            System.out.println(id);
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
