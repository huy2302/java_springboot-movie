package com.example.movie3layer.service;

import com.example.movie3layer.dto.UserAdd;
import com.example.movie3layer.dto.UserDTO;
import com.example.movie3layer.dto.UserShortDTO;
import com.example.movie3layer.model.User;

import java.util.List;

public interface UserService{
    UserDTO getUsernameAndPassword(String uname, String password, String role);

    Boolean checkExistUname(String uname);

    User addUser(String uname, String pass);

    UserShortDTO getUserByUserID (int id);

    UserDTO getUserByID (int id);

    List<UserDTO> getAllUser();

    void editUser(UserDTO userDTO);

    void addUserAdmin(UserAdd userAdd);

    Boolean changePassword(int userId, String password, String new_password);

    void removeUser(int id);
}
