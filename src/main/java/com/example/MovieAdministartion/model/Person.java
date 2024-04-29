package com.example.MovieAdministartion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractModel<Long>{

    private static final long serialVersionUID = -2974953413266908441L;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public TypePersonne getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(TypePersonne typePersonne) {
        this.typePersonne = typePersonne;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public enum TypePersonne {ACTEUR, REALISATEUR}
    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = true, length = 100)
    private String photo;

    @Column(name = "date_naissance")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TypePersonne typePersonne;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="NATIONALITE_ID")
    private Nationality nationality;

    @ManyToMany(mappedBy="acteurs")
    @JsonIgnore
    private List<Movie> movies;

   @OneToMany(mappedBy = "realisateur")
    @JsonIgnore
    private List<Movie> moviesRealises;



    public String toString() {
        return this.prenom + " " + this.nom;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMoviesRealises() {
        return moviesRealises;
    }

    public void setMoviesRealises(List<Movie> moviesRealises) {
        this.moviesRealises = moviesRealises;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}

