package org.example.tpcomplet;

import org.example.tpcomplet.modele.Voiture;
import org.example.tpcomplet.modele.VoitureRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class VoitureRepoTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    VoitureRepo repo;

    @Test
    void ajouterVoiture() {
        Voiture v = new Voiture();
        v.setMarque("MiolaCar");
        v.setModele("Uber");
        v.setCouleur("Blanche");
        v.setImmatricule("M-2020");
        v.setAnnee(2021);
        v.setPrix(180000.0);

        v = repo.save(v);
        em.flush(); // force l'insert DB

        assertThat(v.getId()).isNotNull();
    }

    @Test
    void supprimerVoitures() {
        Voiture v1 = new Voiture();
        v1.setMarque("MiolaCar"); v1.setModele("Uber"); v1.setCouleur("Blanche");
        v1.setImmatricule("M-2020"); v1.setAnnee(2021); v1.setPrix(180000.0);

        Voiture v2 = new Voiture();
        v2.setMarque("MiniCooper"); v2.setModele("Uber"); v2.setCouleur("Rouge");
        v2.setImmatricule("C-2020"); v2.setAnnee(2021); v2.setPrix(180000.0);

        repo.save(v1);
        repo.save(v2);
        em.flush();

        repo.deleteAll();
        em.flush();

        assertThat(repo.findAll()).isEmpty();
    }
}
