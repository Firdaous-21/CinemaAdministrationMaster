package com.example.MovieAdministartion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media extends AbstractModel<Long>{
    private static final long serialVersionUID = 3132573891761750069L;


    @Column(nullable = false, length = 100)
    private String media;
    public enum TypeMedia {IMAGE, VIDEO, DOCUMENT}

    @Column(nullable = true, length = 50)
    @Enumerated(EnumType.STRING)
    private TypeMedia typeMedia;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "Movie_Id")
    private Movie movie;


}
