package org.example.tpcomplet.Service;




import org.example.tpcomplet.modele.Proprietaire;

import java.util.List;

public interface ProprietaireService {
    List<Proprietaire> findAll();
    Proprietaire findById(Long id);
    Proprietaire create(Proprietaire p);
    Proprietaire update(Long id, Proprietaire p);
    void delete(Long id);
}
