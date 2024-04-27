package com.example.movie3layer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "avatar")
    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    )
    private List<Movie> favorites;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "view",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    )
    private List<Movie> view;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Comment> comments;
    public User(String uname, String password, String role) {
        this.username = uname;
        this.password = password;
        this.role = role;
    }
    public User(int user_id, String username, String phone_number, String role, String avatar) {
        this.userID = user_id;
        this.username = username;
        this.phone_number = phone_number;
        this.role = role;
        this.avatar = avatar;
    }

    public User(int userID, String username, String password, String phone_number, String role, String avatar) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.role = role;
        this.avatar = avatar;
    }
}
