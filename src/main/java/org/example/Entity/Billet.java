package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Enumerated;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id_billet")
    private int id;

    @Column(name = "numero_de_place")
    private int numeroDePlace;

    private String evenement;

    @Enumerated
    @Column(name = "type_de_place")
    private TypeDePlace typeDePlace;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "billet_evenement",
            joinColumns = @JoinColumn(name = "id_billet"),
            inverseJoinColumns = @JoinColumn(name = "id_evenement"))
    private List<Evenement> evenementList;

}
