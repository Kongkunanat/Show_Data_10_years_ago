package com.example.Show_Data_10_years_ago.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Images")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String ImageTitle;

    @Column
    private String type;

    @Lob
    private byte[] data;


    public Image(String name, String type, byte[] data) {
        this.ImageTitle = name;
        this.type = type;
        this.data = data;
    }



}



