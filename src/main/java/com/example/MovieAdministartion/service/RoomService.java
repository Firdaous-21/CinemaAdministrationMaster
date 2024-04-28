package com.example.MovieAdministartion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import  com.example.MovieAdministartion.model.Room;
import  com.example.MovieAdministartion.repository.RoomRepository;
@Service
public class RoomService extends AbstractService<Room,Long>{
    @Autowired
    private RoomRepository roomRepository;
    @Override
    protected JpaRepository<Room, Long> getRepository() {
        return roomRepository;
    }

    public Page<Room> findPaginated(int page, int size) {
        return roomRepository.findAll(PageRequest.of(page, size, Sort.by("capacity", "number").descending()));
    }

}
