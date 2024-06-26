package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Adresse {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name="id_adresse")
        private int id;
        private String rue;
        private String ville;

        @Column(name="code_postal")
        private String codePostal;

        @OneToOne(mappedBy = "adresseClient")
        private Client client;

        @OneToOne(mappedBy = "adresse")
        private Evenement evenement;

    }