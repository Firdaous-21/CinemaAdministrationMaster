package com.example.MovieAdministartion.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MovieAdministartion.model.User;




public interface UserRepository extends JpaRepository<User, Long> {
}
