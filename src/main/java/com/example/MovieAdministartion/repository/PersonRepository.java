package com.example.MovieAdministartion.repository;
import com.example.MovieAdministartion.dto.InlinePersonne;
import com.example.MovieAdministartion.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.MovieAdministartion.model.Person.TypePersonne;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlinePersonne.class)
public interface PersonRepository extends JpaRepository<Person,Long> {
    Page<Person> findByTypePersonne(@RequestParam("typePersonne") TypePersonne typePersonne, Pageable pageable);
    Page<Person>findByDateNaissanceGreaterThanEqual(@RequestParam("dateNs") Date dateNs, Pageable pageable);
    Page<Person> findByNomContainingOrPrenomContaining(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, Pageable pageable);
}

