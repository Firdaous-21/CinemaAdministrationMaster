package com.example.MovieAdministartion.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Movie_Rating {
    @EmbeddedId
    private MovieRatingID id;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    public Movie_Rating() {
    }

    public Movie_Rating(MovieRatingID id, Integer score, String comment) {
        this.id = id;
        this.score = score;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Movie_Rating{" +
                "id=" + id +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie_Rating that = (Movie_Rating) o;
        return Objects.equals(id, that.id) && Objects.equals(score, that.score) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, comment);
    }

    public MovieRatingID getId() {
        return id;
    }

    public void setId(MovieRatingID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
