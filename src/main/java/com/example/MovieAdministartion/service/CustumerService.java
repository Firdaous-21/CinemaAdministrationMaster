package com.example.MovieAdministartion.service;

import com.example.MovieAdministartion.model.Customers;
import com.example.MovieAdministartion.repository.CustumersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustumerService extends AbstractService<Customers, Long> {

    @Autowired
    private CustumersRepository customersRepository;

    @Override
    protected JpaRepository<Customers, Long> getRepository() {
        return customersRepository;
    }

}
