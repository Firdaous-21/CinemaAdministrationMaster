package com.example.MovieAdministartion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends AbstractModel<Long> {

    private static final long serialVersionUID = -8008236146679860390L;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;

    @Column(nullable = false, length = 40)
    private int capacity;

    @Column(nullable = false, length = 40)
    private int number;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Seance> seances;
}
