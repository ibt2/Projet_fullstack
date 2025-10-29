package org.example.tpcomplet.modele;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "voitures") // => /api/voitures
public interface VoitureRepo extends CrudRepository<Voiture, Long> {

    // Dérivées Spring Data
    List<Voiture> findByMarque(@Param("marque") String marque);
    List<Voiture> findByCouleur(@Param("couleur") String couleur);
    List<Voiture> findByAnnee(@Param("annee") int annee);
    List<Voiture> findByMarqueAndModele(@Param("marque") String marque, @Param("modele") String modele);
    List<Voiture> findByMarqueOrCouleur(@Param("marque") String marque, @Param("couleur") String couleur);
    List<Voiture> findByMarqueOrderByAnneeAsc(@Param("marque") String marque);

    // JPQL “ends with”
    @Query("select v from Voiture v where v.marque like concat('%', :suffix)")
    List<Voiture> searchByMarqueEndingWith(@Param("suffix") String suffix);

    // JPQL “starts with”
    @Query("select v from Voiture v where v.marque like concat(:prefix, '%')")
    List<Voiture> searchByMarqueStartingWith(@Param("prefix") String prefix);
}
