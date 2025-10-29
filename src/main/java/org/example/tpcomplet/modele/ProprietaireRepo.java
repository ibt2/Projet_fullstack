package org.example.tpcomplet.modele;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "proprietaires") // => /api/proprietaires
public interface ProprietaireRepo extends CrudRepository<Proprietaire, Long> {

    List<Proprietaire> findByNom(@Param("nom") String nom);
    List<Proprietaire> findByPrenom(@Param("prenom") String prenom);
    List<Proprietaire> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query("select p from Proprietaire p where p.nom like concat('%', :suffix)")
    List<Proprietaire> searchByNomEndingWith(@Param("suffix") String suffix);

    @Query("select p from Proprietaire p where p.nom like concat(:prefix, '%')")
    List<Proprietaire> searchByNomStartingWith(@Param("prefix") String prefix);
}
