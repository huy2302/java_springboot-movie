package com.example.movie3layer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int ActorID;
    @Column(name = "actorName")
    private String ActorName; // tên diễn viên
    @Column(name = "age")
    private String Age; // tuổi
    @Column(name = "country")
    private String Country; // quốc tịch
    @Column(name = "gender")
    private String Gender; // giới tính
    @Column(name = "avt", columnDefinition = "TEXT")
    private String Avt; // link avt

    @ManyToMany(mappedBy = "actorList", fetch = FetchType.LAZY)
    private List<Movie> movieList;

    @Column(name = "description", columnDefinition = "TEXT")
    private String Description; // mô tả


}
