package com.example.MovieAdministartion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customers extends AbstractModel<Long>{

    @Column(nullable = false, length = 40)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email ;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;
  }
