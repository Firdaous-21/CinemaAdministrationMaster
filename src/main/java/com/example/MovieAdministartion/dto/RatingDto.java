package com.example.MovieAdministartion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.example.MovieAdministartion.model.Movie_Rating;

public class RatingDto {

    @Min(0)
    @Max(5)
    private Integer score;

    //@Size(max = 255)
    private String comment;

    @NotNull
    private Integer customerId;



    public RatingDto(Integer score, String comment, Integer customerId) {
        this.score = score;
        this.comment = comment;
        this.customerId = customerId;
    }
    public RatingDto(Movie_Rating movieRating) {
        this(movieRating.getScore(), movieRating.getComment(), movieRating.getId().getCustomerId());
    }

    protected RatingDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
