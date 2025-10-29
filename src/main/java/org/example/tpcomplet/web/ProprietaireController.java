package org.example.tpcomplet.web;

import lombok.RequiredArgsConstructor;
import org.example.tpcomplet.modele.Proprietaire;
import org.example.tpcomplet.modele.Voiture;
import org.example.tpcomplet.Service.ProprietaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proprietaires")
@RequiredArgsConstructor
public class ProprietaireController {

    private final ProprietaireService service;

    @GetMapping
    public ResponseEntity<List<Proprietaire>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietaire> get(@PathVariable Long id) {
        Proprietaire p = service.findById(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proprietaire> create(@RequestBody Proprietaire p) {
        Proprietaire created = service.create(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietaire> update(@PathVariable Long id, @RequestBody Proprietaire p) {
        Proprietaire updated = service.update(id, p);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/voitures")
    public ResponseEntity<List<Voiture>> listVoitures(@PathVariable Long id) {
        Proprietaire p = service.findById(id);
        if (p == null) return ResponseEntity.notFound().build();
        // Si LAZY, s'assurer que la collection est initialisée dans le service.
        return ResponseEntity.ok(p.getVoitures());
    }
}
