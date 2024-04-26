package com.example.MovieAdministartion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.MovieAdministartion.model.Person;
import com.example.MovieAdministartion.repository.PersonRepository;
@Service
public class PersonService extends AbstractService<Person,Long> {
    @Autowired
    private PersonRepository personneRepository;

    @Override
    protected JpaRepository<Person, Long> getRepository() {
        return personneRepository;
    }
}



