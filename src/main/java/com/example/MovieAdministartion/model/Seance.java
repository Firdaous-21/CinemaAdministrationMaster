package com.example.MovieAdministartion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seance extends AbstractModel<Long> {
    private static final long serialVersionUID = 6992208427439369561L;

    @Column(name = "date_projection")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateProjection;

    @Column(name = "beginning_hour")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date beginning_hour;

    @Column(name = "ending_hour")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date ending_hour;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "Movie_Id")
    private Movie movie;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "Room_Id")
    private Room room;

    public Date getDateProjection() {
        return dateProjection;
    }

    public void setDateProjection(Date dateProjection) {
        this.dateProjection = dateProjection;
    }

    public Date getBeginning_hour() {
        return beginning_hour;
    }

    public void setBeginning_hour(Date beginning_hour) {
        this.beginning_hour = beginning_hour;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getEnding_hour() {
        return ending_hour;
    }

    public void setEnding_hour(Date ending_hour) {
        this.ending_hour = ending_hour;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
