package com.example.MovieAdministartion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.MovieAdministartion.model.Nationality;
import com.example.MovieAdministartion.repository.NationalityRepository;

@Service
public class NationalityService extends AbstractService<Nationality, Long> {

    @Autowired
    private NationalityRepository nationalityRepository;


    @Override
    protected JpaRepository<Nationality, Long> getRepository() {
        return nationalityRepository;
    }

}
