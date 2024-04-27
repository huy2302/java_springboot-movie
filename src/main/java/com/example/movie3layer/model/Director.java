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
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private int DirectorID;
    @Column(name = "directorName")
    private String DirectorName; // tên đạo diễn
    @Column(name = "age")
    private String Age; // tuổi
    @Column(name = "country")
    private String Country; // quốc tịch
    @Column(name = "gender")
    private String Gender; // giới tính
    @Column(name = "avt")
    private String Avt; // link avt
    @ManyToMany(mappedBy = "directorList", fetch = FetchType.LAZY)
    private List<Movie> movieList;
    @Column(name = "description")
    private String Description; // mô tả


}
