package com.example.MovieAdministartion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovieRatingID implements Serializable {
    private static final long serialVersionUID = 1975988172576602969L;

    @ManyToOne
    private Movie movie;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public MovieRatingID() {
    }

    public MovieRatingID(Movie movie, Integer customerId) {
        this.movie = movie;
        this.customerId = customerId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieRatingID that = (MovieRatingID) o;
        return Objects.equals(movie, that.movie) && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        int result = movie.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MovieRatingID{" +
                "movie=" + movie +
                ", customerId=" + customerId +
                '}';
    }
}
