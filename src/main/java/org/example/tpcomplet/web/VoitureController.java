package org.example.tpcomplet.web;

import lombok.RequiredArgsConstructor;
import org.example.tpcomplet.modele.Voiture;
import org.example.tpcomplet.Service.VoitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
@RequiredArgsConstructor
public class VoitureController {

    private final VoitureService service;

    @GetMapping
    public ResponseEntity<List<Voiture>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> get(@PathVariable Long id) {
        Voiture v = service.findById(id);
        return (v != null) ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }

    /**
     * Créer une voiture. Option: lier à un propriétaire via proprietaireId
     * POST /api/voitures?proprietaireId=1
     */
    @PostMapping
    public ResponseEntity<Voiture> create(@RequestBody Voiture v,
                                          @RequestParam(required = false) Long proprietaireId) {
        Voiture created = service.create(v, proprietaireId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voiture> update(@PathVariable Long id,
                                          @RequestBody Voiture v,
                                          @RequestParam(required = false) Long proprietaireId) {
        Voiture updated = service.update(id, v, proprietaireId);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
