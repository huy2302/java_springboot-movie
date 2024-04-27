package com.example.movie3layer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Lob
    @Column(name = "image_data", columnDefinition = "MEDIUMBLOB")
    private byte[] image_data; // tên movie

    @Column(name = "image_name")
    private String image_name; // tên movie
}
