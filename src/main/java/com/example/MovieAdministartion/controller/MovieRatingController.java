package com.example.MovieAdministartion.controller;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.example.MovieAdministartion.dto.RatingDto;
import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.model.MovieRatingID;
import com.example.MovieAdministartion.model.Movie_Rating;
import com.example.MovieAdministartion.repository.MovieRepository;
import com.example.MovieAdministartion.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;


@RestController
@RequestMapping(path = "/movies/{movieId}/ratings")
public class MovieRatingController {
    private MovieRatingRepository movieratingRepository;
    private MovieRepository movieRepository;

    @Autowired
    public MovieRatingController(MovieRatingRepository movieratingRepository, MovieRepository movieRepository) {
        this.movieratingRepository = movieratingRepository;
        this.movieRepository = movieRepository;
    }

    protected MovieRatingController() {

    }

    /**
     * Create a Film Rating.
     *
     * @param filmId tour identifier
     * @param ratingDto rating data transfer object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovieRating(@PathVariable(value = "movieId") Long filmId, @RequestBody @Validated RatingDto ratingDto) {
        Movie movie = verifyFilm(filmId);
        movieratingRepository.save(new Movie_Rating( new MovieRatingID(movie, ratingDto.getCustomerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }



    /**
     * Lookup a the Ratings for a film.
     *
     * @param filmId Film Identifier
     * @return All Film Ratings as RatingDto's
     */
    @GetMapping
    public List<RatingDto> getAllRatingsForFilm(@PathVariable(value = "movieId") Long filmId) {
        verifyFilm(filmId);
        return movieratingRepository.findByIdMovieId(filmId).stream().map(RatingDto::new).collect(Collectors.toList());
    }

    /**
     * Calculate the average Score of a Film.
     *
     * @param filmId film identifier
     * @return Tuple of "average" and the average value.
     */
    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "movieId") Long filmId) {
        verifyFilm(filmId);
        return Map.of("average",movieratingRepository.findByIdMovieId(filmId).stream()
                .mapToInt(Movie_Rating::getScore).average()
                .orElseThrow(() -> new NoSuchElementException("Tour has no Ratings")));
    }

    /**
     * Verify and return the FilmRating for a particular filmId and Customer
     * @param filmId film identifier
     * @param customerId customer identifier
     * @return the found FilmRating
     * @throws NoSuchElementException if no FilmRating found
     */
    public Movie_Rating verifyFilmRating(Long filmId, Long customerId) throws NoSuchElementException {
        return movieratingRepository.findByIdMovieIdAndIdCustomerId(filmId, customerId).orElseThrow(() ->
                new NoSuchElementException("Film-Rating pair for request("
                        + filmId + " for customer" + customerId));
    }

    /**
     * Verify and return the Film given a filmId.
     *
     * @param filmId tour identifier
     * @return the found Film
     * @throws NoSuchElementException if no Film found.
     */
    private Movie verifyFilm(Long filmId) throws NoSuchElementException {
        return movieRepository.findById(Long.valueOf(filmId)).orElseThrow(() ->
                new NoSuchElementException("Tour does not exist " + filmId));
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }
}


