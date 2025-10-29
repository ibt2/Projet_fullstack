package org.example.tpcomplet;

import org.example.tpcomplet.modele.Proprietaire;
import org.example.tpcomplet.modele.ProprietaireRepo;
import org.example.tpcomplet.modele.Voiture;
import org.example.tpcomplet.modele.VoitureRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TpcompletApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpcompletApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(VoitureRepo voitureRepo, ProprietaireRepo proprietaireRepo) {
        return args -> {
            // 0) Nettoyage (ordre: enfants puis parents)
            voitureRepo.deleteAll();
            proprietaireRepo.deleteAll();

            // 1) Propriétaires
            Proprietaire p1 = new Proprietaire();
            p1.setNom("Ali");
            p1.setPrenom("Hassan");
            p1.setVoitures(new ArrayList<>());  // évite NPE si tu ajoutes des voitures à la main

            Proprietaire p2 = new Proprietaire();
            p2.setNom("Najat");
            p2.setPrenom("Bani");
            p2.setVoitures(new ArrayList<>());

            p1 = proprietaireRepo.save(p1);
            p2 = proprietaireRepo.save(p2);

            // 2) Voitures (prix en Double, immatricule optionnel)
            Voiture v1 = new Voiture();
            v1.setMarque("Toyota");
            v1.setModele("Corolla");
            v1.setCouleur("Grise");
            v1.setImmatricule("A-1-9090");
            v1.setAnnee(2018);
            v1.setPrix(95000.0);
            v1.setProprietaire(p1);

            Voiture v2 = new Voiture();
            v2.setMarque("Ford");
            v2.setModele("Fiesta");
            v2.setCouleur("Rouge");
            v2.setImmatricule("A-2-8090");
            v2.setAnnee(2015);
            v2.setPrix(90000.0);
            v2.setProprietaire(p1);

            Voiture v3 = new Voiture();
            v3.setMarque("Honda");
            v3.setModele("CRV");
            v3.setCouleur("Bleu");
            v3.setImmatricule("A-3-7090");
            v3.setAnnee(2016);
            v3.setPrix(140000.0);
            v3.setProprietaire(p2);

            // 3) Sauvegarde en lot
            voitureRepo.saveAll(List.of(v1, v2, v3));

            // (Optionnel) si tu tiens à maintenir la relation côté propriétaire :
            // p1.getVoitures().addAll(List.of(v1, v2));
            // p2.getVoitures().add(v3);
            // proprietaireRepo.saveAll(List.of(p1, p2));
        };
    }
}
