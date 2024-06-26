package org.example;


import org.example.Entity.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.example.Entity.TypeDePlace.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("billetterie");
        EntityManager em = emf.createEntityManager();

        Adresse adresse1 = Adresse.builder()
                .ville("Lille")
                .rue("Rue du jardin")
                .codePostal("59600")
                .build();

        Adresse adresse2 = Adresse.builder()
                .ville("Bruxelle")
                .rue("Rue boilot")
                .codePostal("3800")
                .build();

        Client client1 = Client.builder()
                .age(25)
                .adresseClient(adresse1)
                .nom("CHITOU")
                .prenom("Abdel")
                .telephone("0767128503")
                .build();

        Client client2 = Client.builder()
                .age(30)
                .adresseClient(adresse1)
                .nom("FASNA")
                .prenom("Nassir")
                .telephone("0712348503")
                .build();

        Billet billet1 = Billet.builder()
                .client(client2)
                .typeDePlace(TypeDePlace.VIP)
                .numeroDePlace(235)
                .build();

        Evenement evenement1 = Evenement.builder()
                .nom("Crazy Party")
                .adresse(adresse2)
                .Date(LocalDate.ofEpochDay(2024-12-15))
                .heure(LocalTime.ofSecondOfDay(15))
                .billetList(Collections.singletonList(billet1))
                .nbrPlace(3)
                .build();

        em.getTransaction().begin();

        em.persist(adresse1);
        em.persist(adresse2);
        em.persist(client1);
        em.persist(client2);
        em.persist(billet1);
        em.persist(evenement1);

        em.getTransaction().commit();
        em.close();


    }
}