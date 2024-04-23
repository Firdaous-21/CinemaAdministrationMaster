package com.example.MovieAdministartion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.MovieAdministartion.model.Media;
import com.example.MovieAdministartion.repository.MediaRepository;


@Service
public class MediaService extends AbstractService<Media, Long> {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    protected JpaRepository<Media, Long> getRepository() {
        return mediaRepository;
    }

}