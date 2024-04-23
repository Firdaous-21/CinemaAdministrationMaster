package com.example.MovieAdministartion.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.MovieAdministartion.model.Type;
import com.example.MovieAdministartion.repository.TypeRepository;

@Service
public class TypeService extends AbstractService<Type, Long> {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    protected JpaRepository<Type, Long> getRepository() {
        return typeRepository;
    }

}
