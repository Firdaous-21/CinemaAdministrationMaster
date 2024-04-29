package com.example.MovieAdministartion.service;

import com.example.MovieAdministartion.model.Client;
import com.example.MovieAdministartion.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService<Client, Long> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    protected JpaRepository<Client, Long> getRepository() {
        return clientRepository;
    }

}