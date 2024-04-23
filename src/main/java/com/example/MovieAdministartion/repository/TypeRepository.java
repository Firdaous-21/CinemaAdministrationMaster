package com.example.MovieAdministartion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.MovieAdministartion.model.Type ;


@CrossOrigin("http://localhost:4200")
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}

