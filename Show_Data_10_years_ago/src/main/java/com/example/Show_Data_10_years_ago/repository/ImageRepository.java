package com.example.Show_Data_10_years_ago.repository;


import com.example.Show_Data_10_years_ago.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
