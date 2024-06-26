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

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name ="id_client")
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String telephone;

    @OneToOne
    @JoinColumn(name ="id_adresse")
    private Adresse adresseClient;

}
