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
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private int WriterID;
    @Column(name = "writerName")
    private String WriterName; // tên diễn viên
    @Column(name = "age")
    private String Age; // tuổi
    @Column(name = "country")
    private String Country; // quốc tịch
    @Column(name = "gender")
    private String Gender; // giới tính
    @Column(name = "avt")
    private String Avt; // link avt
    @ManyToMany(mappedBy = "writerList", fetch = FetchType.LAZY)
    private List<Movie> movieList;
    @Column(name = "description")
    private String Description; // mô tả

}
