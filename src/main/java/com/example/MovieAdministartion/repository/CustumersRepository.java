package com.example.MovieAdministartion.repository;

import com.example.MovieAdministartion.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "client", path = "client")
public interface CustumersRepository extends JpaRepository<Customers, Long> {

}
