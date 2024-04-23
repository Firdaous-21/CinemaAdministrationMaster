package com.example.MovieAdministartion.service;

import com.example.MovieAdministartion.model.Room;
import com.example.MovieAdministartion.model.Seance;
import com.example.MovieAdministartion.repository.SeanceRepository;
import com.example.MovieAdministartion.repository.CustumersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class SeanceService extends AbstractService<Seance,Long>{
    @Autowired
    private  SeanceRepository seanceRepository;

    @Override
    protected JpaRepository<Seance, Long> getRepository() {
        return seanceRepository;
    }

    public List<Seance> getSeancesParDate(Date date){
        return seanceRepository.findByDateProjection(date);
    }

}

