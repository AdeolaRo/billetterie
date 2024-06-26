package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_evenement")
    private int id;
    private String nom;

    @Column(name = "date")
    private LocalDate Date;

    @Column(name = "Heure")
    private LocalTime heure;

    @Column(name = "nbr_place")
    private double nbrPlace;


    @OneToOne
    @JoinColumn(name ="id_adresse")
    private Adresse adresse;

    @ManyToMany(mappedBy = "evenementList")
    private List<Billet> billetList;

}
