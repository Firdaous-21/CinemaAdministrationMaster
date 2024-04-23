package com.example.MovieAdministartion.dto;

import com.example.MovieAdministartion.model.Nationality;
import com.example.MovieAdministartion.model.Person;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;


@Projection(name = "inlinePersonne", types = { Person.class })
public interface InlinePersonne {
	Long getId();
	String getNom();
	String getPrenom();
	String getPhoto();
	Date getDateNaissance();
	Person.TypePersonne getTypePersonne();
	Date getAddedDate();
	Nationality getNationalite();
}
