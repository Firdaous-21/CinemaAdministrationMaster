package com.example.MovieAdministartion.repository;
import com.example.MovieAdministartion.dto.InlineFilm;
import com.example.MovieAdministartion.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineFilm.class)
public interface MovieRepository extends JpaRepository<Movie,Long> {

}
