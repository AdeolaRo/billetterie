package org.example.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class IHM {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("billetterie");
    private static EntityManager em = emf.createEntityManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        begin();
    }

    public static void begin() {
        while (true) {
            afficheMenu();
            String entry = sc.nextLine();
            switch (entry) {
                case "1" -> menuAdresse();
                case "2" -> menuClient();
                case "3" -> menuBillet();
                case "4" -> menuEvenement();
                case "0" -> {
                    System.out.println("Au revoir !!!");
                    em.close();
                    emf.close();
                    return;
                }
                default -> System.out.println("Choix invalide !!!!");
            }
        }
    }

    public static void afficheMenu() {
        System.out.println(" ====== Billetterie ======");
        System.out.println();
        System.out.println("1) Menu Adresse.");
        System.out.println("2) Menu Client");
        System.out.println("3) Menu Billet");
        System.out.println("4) Menu Evenement");
        System.out.println("0) Quitter");
        System.out.println();
        System.out.print("Faites votre choix : ");
    }

    public static void menuAdresse() {
        while (true) {
            System.out.println();
            System.out.println("***** Menu Adresse *****");
            System.out.println("1- Créer une adresse");
            System.out.println("2- Afficher les adresses");
            System.out.println("3- Mettre à jour une adresse");
            System.out.println("4- Supprimer une adresse");
            System.out.println("0) Retour menu principal");

            String saisie = sc.nextLine();
            switch (saisie) {
                case "1" -> createAdress();
                case "2" -> showAdresses();
                case "3" -> updateAdress();
                case "4" -> deleteAdress();
                case "0" -> {
                    return;
                }
                default -> System.out.println("** Mauvais choix **");
            }
        }
    }

    public static Adresse createAdress() {
        Adresse adresse = new Adresse();

        System.out.println("Ville : ");
        adresse.setVille(sc.nextLine());

        System.out.println("Rue : ");
        adresse.setRue(sc.nextLine());

        System.out.println("Code Postal : ");
        adresse.setCodePostal(sc.nextLine());

        em.getTransaction().begin();
        em.persist(adresse);
        em.getTransaction().commit();

        System.out.println("Adresse créée avec succès !");
        return adresse;
    }



    public static void showAdresses() {
        List<Adresse> adresses = em.createQuery("SELECT a FROM Adresse a", Adresse.class).getResultList();
        System.out.println("***** Liste des Adresses *****");
        System.out.println(adresses);
    }



    public static void updateAdress() {
        System.out.println("Entrez l'ID de l'adresse à mettre à jour : ");
        int id = Integer.parseInt(sc.nextLine());
        Adresse adresse = em.find(Adresse.class, id);

        if (adresse != null) {
            System.out.println("Ville à corriger : ");
            adresse.setVille(sc.nextLine());

            System.out.println("La nouvelle Rue : ");
            adresse.setRue(sc.nextLine());

            System.out.println("Le nouveau Code Postal : ");
            adresse.setCodePostal(sc.nextLine());

            em.getTransaction().begin();
            em.merge(adresse);
            em.getTransaction().commit();

            System.out.println("Adresse mise à jour !");
        } else {
            System.out.println("Adresse non trouvée !");
        }
    }


    public static void deleteAdress() {
        System.out.println("Entrez l'ID de l'adresse à supprimer : ");
        int id = Integer.parseInt(sc.nextLine());
        Adresse adresse = em.find(Adresse.class, id);

        if (adresse != null) {
            em.getTransaction().begin();
            em.remove(adresse);
            em.getTransaction().commit();
            System.out.println("Adresse supprimée avec succès !");
        } else {
            System.out.println("Adresse non trouvée !");
        }
    }

    public static void menuClient() {
        while (true) {
            System.out.println();
            System.out.println("***** Menu Client *****");
            System.out.println("1- Créer un Client");
            System.out.println("2- Afficher les Clients");
            System.out.println("3- Mettre à jour un Client");
            System.out.println("4- Supprimer une Client");
            System.out.println("0) Retour menu principal");

            String saisie = sc.nextLine();
            switch (saisie) {
                case "1" -> createClient();
                case "2" -> showClients();
                case "3" -> updateClient();
                case "4" -> deleteClient();
                case "0" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("** Mauvais choix **");
            }
        }
    }

    public static void createClient() {
        Client client = new Client();

        System.out.println("Nom : ");
        client.setNom(sc.nextLine());

        System.out.println("Prénom : ");
        client.setPrenom(sc.nextLine());

        System.out.println("Âge : ");
        client.setAge(Integer.parseInt(sc.nextLine()));

        System.out.println("Téléphone : ");
        client.setTelephone(sc.nextLine());

        System.out.println("Adresse : ");
        Adresse adresse = createAdress();
        client.setAdresseClient(adresse);

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();

        System.out.println("Client créé avec succès !");
    }

    public static void showClients() {
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        System.out.println("***** Liste des Clients *****");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public static void updateClient() {
        System.out.println("Entrez l'ID du client à mettre à jour : ");
        int id = Integer.parseInt(sc.nextLine());
        Client client = em.find(Client.class, id);

        if (client != null) {
            System.out.println("Nouveau Nom : ");
            client.setNom(sc.nextLine());

            System.out.println("Nouveau Prénom : ");
            client.setPrenom(sc.nextLine());

            System.out.println("Nouvel Âge : ");
            client.setAge(Integer.parseInt(sc.nextLine()));

            System.out.println("Nouveau Téléphone : ");
            client.setTelephone(sc.nextLine());

            System.out.println("Nouvelle Adresse : ");
            Adresse adresse = createAdress();
            client.setAdresseClient(adresse);

            em.getTransaction().begin();
            em.merge(client);
            em.getTransaction().commit();

            System.out.println("Client mis à jour avec succès !");
        } else {
            System.out.println("Client non trouvé !");
        }
    }

    public static void deleteClient() {
        System.out.println("Entrez l'ID du client à supprimer : ");
        int id = Integer.parseInt(sc.nextLine());
        Client client = em.find(Client.class, id);

        if (client != null) {
            em.getTransaction().begin();
            em.remove(client);
            em.getTransaction().commit();
            System.out.println("Client supprimé avec succès !");
        } else {
            System.out.println("Client non trouvé !");
        }
    }

    public static void menuBillet() {
        while (true) {
            System.out.println();
            System.out.println("***** Menu Billet *****");
            System.out.println("1- Créer un billet");
            System.out.println("2- Afficher les billets");
            System.out.println("3- Mettre à jour un billet");
            System.out.println("4- Supprimer un billet");
            System.out.println("0) Retour menu principal");

            String saisie = sc.nextLine();
            switch (saisie) {
                case "1" -> createBillet();
                case "2" -> showBillets();
                case "3" -> updateBillet();
                case "4" -> deleteBillet();
                case "0" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("** Mauvais choix **");
            }
        }
    }

    public static void createBillet() {
        Billet billet = new Billet();

        System.out.println("Numéro de place : ");
        billet.setNumeroDePlace(Integer.parseInt(sc.nextLine()));

        System.out.println("Événement : ");
        billet.setEvenement(sc.nextLine());

        System.out.println("Type de place (STANDARD, VIP, PREMIUM) : ");
        billet.setTypeDePlace(TypeDePlace.valueOf(sc.nextLine().toUpperCase()));

        System.out.println("ID du client : ");
        int clientId = Integer.parseInt(sc.nextLine());
        Client client = em.find(Client.class, clientId);
        billet.setClient(client);

        em.getTransaction().begin();
        em.persist(billet);
        em.getTransaction().commit();

        System.out.println("Billet créé avec succès !");
    }

    public static void showBillets() {
        List<Billet> billets = em.createQuery("SELECT b FROM Billet b", Billet.class).getResultList();
        System.out.println("***** Liste des Billets *****");
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }

    public static void updateBillet() {
        System.out.println("Entrez l'ID du billet à mettre à jour : ");
        int id = Integer.parseInt(sc.nextLine());
        Billet billet = em.find(Billet.class, id);

        if (billet != null) {
            System.out.println("Nouveau Numéro de place : ");
            billet.setNumeroDePlace(Integer.parseInt(sc.nextLine()));

            System.out.println("Nouvel Événement : ");
            billet.setEvenement(sc.nextLine());

            System.out.println("Nouveau Type de place (STANDARD, VIP, PREMIUM) : ");
            billet.setTypeDePlace(TypeDePlace.valueOf(sc.nextLine().toUpperCase()));

            System.out.println("Nouvel ID du client : ");
            int clientId = Integer.parseInt(sc.nextLine());
            Client client = em.find(Client.class, clientId);
            billet.setClient(client);

            em.getTransaction().begin();
            em.merge(billet);
            em.getTransaction().commit();

            System.out.println("Billet mis à jour avec succès !");
        } else {
            System.out.println("Billet non trouvé !");
        }
    }

    public static void deleteBillet() {
        System.out.println("Entrez l'ID du billet à supprimer : ");
        int id = Integer.parseInt(sc.nextLine());
        Billet billet = em.find(Billet.class, id);

        if (billet != null) {
            em.getTransaction().begin();
            em.remove(billet);
            em.getTransaction().commit();
            System.out.println("Billet supprimé avec succès !");
        } else {
            System.out.println("Billet non trouvé !");
        }
    }

    public static void menuEvenement() {
        while (true) {
            System.out.println();
            System.out.println("***** Menu Evenement *****");
            System.out.println("1- Créer un Evenement");
            System.out.println("2- Afficher les Evenements");
            System.out.println("3- Mettre à jour un Evenement");
            System.out.println("4- Supprimer un Evenement");
            System.out.println("5- Ajouter un billet à un Evenement");
            System.out.println("0) Retour menu principal");

            String saisie = sc.nextLine();
            switch (saisie) {
                case "1" -> createEvenement();
                case "2" -> showEvenements();
                case "3" -> updateEvenement();
                case "4" -> deleteEvenement();
                case "5" -> addBilletToEvenement();
                case "0" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("** Mauvais choix **");
            }
        }
    }

    public static void createEvenement() {
        Evenement evenement = new Evenement();

        System.out.println("Nom : ");
        evenement.setNom(sc.nextLine());

        System.out.println("Date (YYYY-MM-DD) : ");
        evenement.setDate(LocalDate.parse(sc.nextLine()));

        System.out.println("Heure (HH:MM:SS) : ");
        evenement.setHeure(LocalTime.parse(sc.nextLine()));

        System.out.println("Nombre de places : ");
        evenement.setNbrPlace(Double.parseDouble(sc.nextLine()));

        System.out.println("Adresse : ");
        Adresse adresse = createAdress();
        evenement.setAdresse(adresse);

        em.getTransaction().begin();
        em.persist(evenement);
        em.getTransaction().commit();

        System.out.println("Événement créé avec succès !");
    }

    public static void addBilletToEvenement (){

        em.getTransaction().begin();

        System.out.println("Entrez l'ID du billet ");
        int id = Integer.parseInt(sc.nextLine());
        Billet billet = em.find(Billet.class, id);
        System.out.println("Entrez l'ID de l'evenement ");
        int id1 = Integer.parseInt(sc.nextLine());
        Evenement evenement = em.find(Evenement.class, id1);
        evenement.addBillet(billet);
        billet.addEvenement(evenement);

        em.getTransaction().commit();

    }





    public static void showEvenements() {
        List<Evenement> evenements = em.createQuery("SELECT e FROM Evenement e", Evenement.class).getResultList();
        System.out.println("***** Liste des Événements *****");
        for (Evenement evenement : evenements) {
            System.out.println(evenement);
        }
    }

    public static void updateEvenement() {
        System.out.println("Entrez l'ID de l'événement à mettre à jour : ");
        int id = Integer.parseInt(sc.nextLine());
        Evenement evenement = em.find(Evenement.class, id);

        if (evenement != null) {
            System.out.println("Nouveau Nom : ");
            evenement.setNom(sc.nextLine());

            System.out.println("Nouvelle Date (YYYY-MM-DD) : ");
            evenement.setDate(LocalDate.parse(sc.nextLine()));

            System.out.println("Nouvelle Heure (HH:MM:SS) : ");
            evenement.setHeure(LocalTime.parse(sc.nextLine()));

            System.out.println("Nouveau Nombre de places : ");
            evenement.setNbrPlace(Double.parseDouble(sc.nextLine()));

            System.out.println("Nouvelle Adresse : ");
            Adresse adresse = createAdress();
            evenement.setAdresse(adresse);

            em.getTransaction().begin();
            em.merge(evenement);
            em.getTransaction().commit();

            System.out.println("Événement mis à jour avec succès !");
        } else {
            System.out.println("Événement non trouvé !");
        }
    }

    public static void deleteEvenement() {
        System.out.println("Entrez l'ID de l'événement à supprimer : ");
        int id = Integer.parseInt(sc.nextLine());
        Evenement evenement = em.find(Evenement.class, id);

        if (evenement != null) {
            em.getTransaction().begin();
            em.remove(evenement);
            em.getTransaction().commit();
            System.out.println("Événement supprimé avec succès !");
        } else {
            System.out.println("Événement non trouvé !");
        }
    }
}
