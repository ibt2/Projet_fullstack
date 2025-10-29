package org.example.tpcomplet.Service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tpcomplet.Service.ProprietaireService;
import org.example.tpcomplet.exception.NotFoundException;
import org.example.tpcomplet.modele.Proprietaire;
import org.example.tpcomplet.modele.ProprietaireRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProprietaireServiceImpl implements ProprietaireService {

    private final ProprietaireRepo repo;  // <-- corrige : injecter le REPO

    @Override @Transactional(readOnly = true)
    public List<Proprietaire> findAll() {
        return (List<Proprietaire>) repo.findAll();
    }

    @Override @Transactional(readOnly = true)
    public Proprietaire findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Proprietaire id " + id + " introuvable"));
    }

    @Override
    public Proprietaire create(Proprietaire p) {
        return repo.save(p);
    }

    @Override
    public Proprietaire update(Long id, Proprietaire p) {
        Proprietaire existing = findById(id);
        existing.setNom(p.getNom());
        existing.setPrenom(p.getPrenom());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.delete(findById(id));
    }
}
