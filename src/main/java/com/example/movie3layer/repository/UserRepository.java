package com.example.movie3layer.repository;

import com.example.movie3layer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT user.* FROM user WHERE user.role = :role AND user.username = :name AND user.password = :password", nativeQuery = true)
    Optional<User> findUserByUsernamePass(String name, String password, String role);
    @Query(value = "SELECT user.* FROM user WHERE user.role = :role AND user.user_id = :id AND user.password = :password", nativeQuery = true)
    Optional<User> findUserByIDPass(int id, String password, String role);
    @Query(value = "SELECT user.* FROM user WHERE user.username = :name", nativeQuery = true)
    Optional<User> findUserByUsername(String name);

    @Query(value = "SELECT user.* FROM user WHERE user.role = :role AND user.username = :name AND user.password = :password", nativeQuery = true)
    User getUserByUnamePass(String name, String password, String role);
    @Query(value = "SELECT user.* FROM user WHERE user.username = :name", nativeQuery = true)
    User getUserByUname(String name);
    @Query("select u from User u where u.userID = :id")
    User getUserByUserID(int id);
    @Modifying
    @Query(value = "UPDATE `movie`.`user` SET `avatar` = :avatar, `phone_number` = :phone_number, `role` = :role, `username` = :name WHERE (`user_id` = :id);", nativeQuery = true)
    void updateUser(@Param("id") int id, @Param("name") String name, @Param("phone_number") String phone_number, @Param("role") String role, @Param("avatar") String avatar);

    @Modifying
    @Query(value = "UPDATE `movie`.`user` SET `password` = :password WHERE (`user_id` = :id);", nativeQuery = true)
    void changePassword(@Param("id") int id, @Param("password") String password);


}
