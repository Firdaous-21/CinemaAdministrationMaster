package com.example.MovieAdministartion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import com.example.MovieAdministartion.model.Movie;
import com.example.MovieAdministartion.repository.MovieRepository;

@Service
public class MovieService extends AbstractService<Movie,Long> {

    @Autowired
    private MovieRepository MovieRepository;

    @Override
    protected JpaRepository<Movie, Long> getRepository() {
        return MovieRepository;
    }

}
