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
}
