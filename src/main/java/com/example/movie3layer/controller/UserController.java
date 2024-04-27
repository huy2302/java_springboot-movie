package com.example.movie3layer.controller;

import com.example.movie3layer.dto.UserAdd;
import com.example.movie3layer.dto.UserDTO;
import com.example.movie3layer.exception.ErrorResponse;
import com.example.movie3layer.model.User;
import com.example.movie3layer.model.UserLogin;
import com.example.movie3layer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("http://127.0.0.1:5173")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;


//    login with uname, password
    @PostMapping("login")
    public ResponseEntity<?> loginWithUnamePass (
            @RequestBody UserLogin userLogin
            ) {
        UserDTO userDTO = userService.getUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword(), userLogin.getRole());

        if (userDTO.getRole().equals("1")) {
            return new ResponseEntity<>(
                    userDTO,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Không tồn tại tài khoản",
                HttpStatus.OK
        );
    }
    @PostMapping("login-admin")
    public ResponseEntity<?> loginAdmin (
            @RequestBody UserLogin userLogin
    ) {
        UserDTO userDTO = userService.getUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword(), userLogin.getRole());

        if (userDTO.getRole().equals("0")) {
            return new ResponseEntity<>(
                    userDTO,
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Không tồn tại tài khoản",
                HttpStatus.OK
        );
    }
    @PostMapping("register")
    public ResponseEntity<?> registerWithUnamePass (
            @RequestBody UserLogin userLogin
            ) {
        User user = userService.addUser(userLogin.getUsername(), userLogin.getPassword());

        if (user != null) {
            return new ResponseEntity<>(
                    new ErrorResponse(
                            HttpStatus.OK,
                            "Tạo tài khoản thành công!"
                    ),
                    HttpStatus.OK
            );
        }
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGenre() {
        return new ResponseEntity<>(
                userService.getAllUser(),
                HttpStatus.OK
        );
    }

//    lấy user bằng user_id
    @GetMapping("/detail")
    public ResponseEntity<?> getUserDetail(
            @RequestParam("id") int id
    ) {
        return new ResponseEntity<>(
                userService.getUserByID(id),
                HttpStatus.OK
        );
    }
//    cập nhật admin
    @PostMapping("/edit")
    public ResponseEntity<?> getUserDetail(
            @RequestBody UserDTO userDTO
    ) {
        try {
            userService.editUser(userDTO);
            return new ResponseEntity<>(
                    "Cập nhật thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.OK
            );

        }
    }
//    đổi mật khẩu
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestParam String user_id,
            @RequestParam String password,
            @RequestParam String new_password
    ) {
        String message = "";
        try {
            if (userService.changePassword(Integer.parseInt(user_id), password, new_password)) {
                message = "Đổi mật khẩu thành công";
            } else {
                message = "Đổi mật khẩu thất bại";
            }
            return new ResponseEntity<>(
                    message,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.OK
            );

        }
    }
//    thêm mới người dùng

    @PostMapping("/add")
    public ResponseEntity<?> addUser(
            @RequestBody UserAdd userAdd
    ) {
        try {
            userService.addUserAdmin(userAdd);
            return new ResponseEntity<>(
                    "Thêm mới thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.OK
            );

        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(
            @RequestParam int id
    ) {
        try {
            userService.removeUser(id);
            return new ResponseEntity<>(
                    "Xóa thành công",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e,
                    HttpStatus.OK
            );

        }
    }
}
