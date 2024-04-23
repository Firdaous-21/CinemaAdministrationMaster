package com.example.MovieAdministartion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nationality extends AbstractModel<Long> {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String label;

    @OneToMany(mappedBy = "nationality")
    private List<Person> personList;

    public String toString() {
        return this.label;
    }

}
