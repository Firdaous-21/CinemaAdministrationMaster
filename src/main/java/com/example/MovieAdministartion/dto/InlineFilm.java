package com.example.MovieAdministartion.dto;

import com.example.MovieAdministartion.model.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;


@Projection(name = "inlineFilm", types = { Movie.class })
public interface InlineFilm {
	Long getId();
	String getTitre();
	int getAnnee();
	int getDuree();
	Type getType();
	Nationality getNationality();
	Person getRealisateur();
	List<Person> getActeurs();
	List<Seance> getSeances();
	Date getAddedDate();
}
