package org.example.tpcomplet.modele;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proprietaires")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proprietaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pas de @NonNull pour ne pas imposer des constructeurs Lombok stricts
    private String nom;
    private String prenom;

    @OneToMany(
            mappedBy = "proprietaire",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    // On N'ignore PAS ici pour pouvoir renvoyer la liste des voitures d'un propriétaire.
    private List<Voiture> voitures = new ArrayList<>();
}
