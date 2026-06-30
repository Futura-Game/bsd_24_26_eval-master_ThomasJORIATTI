package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.ProduitView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ProduitView.Public.class)
    private Integer id;

    @JsonView(ProduitView.Public.class)
    private String nom;

    @JsonView(ProduitView.Public.class)
    private String urlImage;

    // Changé en double pour permettre les calculs d'écarts de prix plus tard
    @JsonView(ProduitView.Public.class)
    private double prix;
}
