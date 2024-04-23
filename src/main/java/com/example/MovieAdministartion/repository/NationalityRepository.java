package com.example.MovieAdministartion.repository;
import com.example.MovieAdministartion.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin("http://localhost:4200")
@Repository

public interface NationalityRepository extends JpaRepository<Nationality,Long>{
    Page<Nationality> findByLabelStartsWith(@RequestParam("label") String label, Pageable pageable);
}
