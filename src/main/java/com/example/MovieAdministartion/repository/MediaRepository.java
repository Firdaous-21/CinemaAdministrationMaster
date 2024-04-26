package com.example.MovieAdministartion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.MovieAdministartion.model.Media;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

}