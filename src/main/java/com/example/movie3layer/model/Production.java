package com.example.movie3layer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_id")
    private int ProductionID;
    @Column(name = "production")
    private String Production; // tên studio sản xuất
    @Column(name = "year")
    private String Year; // năm ra mắt
    @Column(name = "country")
    private String Country; // quốc gia
    @Column(name = "description")
    private String Description; // mô tả

}
