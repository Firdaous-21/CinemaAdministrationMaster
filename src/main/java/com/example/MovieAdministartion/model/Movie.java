package com.example.MovieAdministartion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie extends AbstractModel<Long> {
    private static final long serialVersionUID = 2996009286487492970L;
    @Column(nullable = false)
    private int duration;

    @Column(nullable = false, length = 50)
    private String title;


    private String description;

    @Column(nullable = false)
    private int year;

    private Timestamp release_year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Genre_Id")
    @JsonProperty
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Nationnality_Id")
    @JsonProperty
    private Nationality nationality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIRECTOR_ID")
    private Person realisateur;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "FILM_ACTEUR",
            joinColumns = @JoinColumn(name = "Movie_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTEUR_ID", referencedColumnName = "ID"))
    private List<Person> acteurs;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Seance> seances;

    @OneToMany(mappedBy = "movie", cascade = {CascadeType.ALL})
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Media> medias;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;

}