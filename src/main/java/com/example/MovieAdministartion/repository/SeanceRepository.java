package com.example.MovieAdministartion.repository;
import com.example.MovieAdministartion.dto.InlineFilm;
import com.example.MovieAdministartion.model.Seance;
import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.Temporal;



import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineFilm.class)
public interface SeanceRepository extends JpaRepository<Seance,Long> {
    List<Seance> findByDateProjection(@Temporal(TemporalType.DATE) Date dateProjection);
}
