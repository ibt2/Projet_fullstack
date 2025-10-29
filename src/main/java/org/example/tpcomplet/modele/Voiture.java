package org.example.tpcomplet.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "voitures")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "proprietaire")
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String modele;

    private String couleur;

    private Integer annee;


    private Double prix;


    private String immatricule;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "proprietaire_id")
    @JsonIgnore
    private Proprietaire proprietaire;


}
