package com.example.MovieAdministartion.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.MovieAdministartion.model.Movie_Rating;
import  com.example.MovieAdministartion.model.MovieRatingID;



@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(exported = false)
public interface MovieRatingRepository extends CrudRepository<Movie_Rating, MovieRatingID>{
    /**
     * Lookup all the FilmRatings for a tour.
     *
     * @param filmId is the tour Identifier
     * @return a List of any found FilmRatings
     */
    //Optional<Movie_Rating> findById(MovieRatingID filmId);
    List<Movie_Rating> findByIdMovieId(long filmId);

    /**
     * Lookup a TourRating by the TourId and Customer Id
     * @param customerId customer identifier
     * @return Optional of found FilmRatings.
     */
    Optional<Movie_Rating> findByIdMovieIdAndIdCustomerId(Long tourId, Long customerId);
}
