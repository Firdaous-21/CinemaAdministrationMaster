package com.example.MovieAdministartion.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MovieAdministartion.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(@Param("email") String email );
    User findByEmail(String email);
    long count();
}