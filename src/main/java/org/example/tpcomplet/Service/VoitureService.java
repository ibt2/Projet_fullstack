package org.example.tpcomplet.Service;



import org.example.tpcomplet.modele.Voiture;

import java.util.List;

public interface VoitureService {
    List<Voiture> findAll();
    Voiture findById(Long id);
    Voiture create(Voiture v, Long proprietaireId); // peut être null
    Voiture update(Long id, Voiture v, Long proprietaireId); // peut être null
    void delete(Long id);
}
