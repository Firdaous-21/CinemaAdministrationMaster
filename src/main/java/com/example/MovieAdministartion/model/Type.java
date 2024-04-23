package com.example.MovieAdministartion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Type extends AbstractModel<Long> {

    private static final long serialVersionUID = -5754835234259566904L;
    @Column(nullable = false, length = 50)
    private String label;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Movie> movieList;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
