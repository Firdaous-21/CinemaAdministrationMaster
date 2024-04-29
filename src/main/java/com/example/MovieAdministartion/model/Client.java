package com.example.MovieAdministartion.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client extends AbstractModel<Long> {

    @Column(nullable = false, length = 40)
    private String firstname;

    @Column(nullable = false, length = 40)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;

}