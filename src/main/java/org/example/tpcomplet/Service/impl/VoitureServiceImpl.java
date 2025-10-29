package org.example.tpcomplet.Service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tpcomplet.Service.VoitureService;
import org.example.tpcomplet.exception.NotFoundException;
import org.example.tpcomplet.modele.Proprietaire;
import org.example.tpcomplet.modele.ProprietaireRepo;
import org.example.tpcomplet.modele.Voiture;
import org.example.tpcomplet.modele.VoitureRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VoitureServiceImpl implements VoitureService {

    private final VoitureRepo voitureRepo;            // <-- corrige : injecter le REPO
    private final ProprietaireRepo proprietaireRepo;  // <-- corrige : injecter le REPO

    @Override @Transactional(readOnly = true)
    public List<Voiture> findAll() {
        return (List<Voiture>) voitureRepo.findAll();
    }

    @Override @Transactional(readOnly = true)
    public Voiture findById(Long id) {
        return voitureRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Voiture id " + id + " introuvable"));
    }

    @Override
    public Voiture create(Voiture v, Long proprietaireId) {
        if (proprietaireId != null) {
            Proprietaire p = proprietaireRepo.findById(proprietaireId)
                    .orElseThrow(() -> new NotFoundException("Proprietaire id " + proprietaireId + " introuvable"));
            v.setProprietaire(p);
        } else {
            v.setProprietaire(null);
        }
        return voitureRepo.save(v);
    }

    @Override
    public Voiture update(Long id, Voiture v, Long proprietaireId) {
        Voiture existing = findById(id);
        existing.setMarque(v.getMarque());
        existing.setModele(v.getModele());
        existing.setCouleur(v.getCouleur());
        existing.setImmatricule(v.getImmatricule());
        existing.setAnnee(v.getAnnee());
        existing.setPrix(v.getPrix());

        if (proprietaireId != null) {
            Proprietaire p = proprietaireRepo.findById(proprietaireId)
                    .orElseThrow(() -> new NotFoundException("Proprietaire id " + proprietaireId + " introuvable"));
            existing.setProprietaire(p);
        } else {
            existing.setProprietaire(null);
        }
        return voitureRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        voitureRepo.delete(findById(id));
    }
}
