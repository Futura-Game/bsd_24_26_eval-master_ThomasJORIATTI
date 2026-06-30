package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.SessionView;
import edu.ban7.demo_spring_bsd_24_26.view.QuestionView; // Ajout de l'import
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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({SessionView.AvecQuestions.class, QuestionView.Public.class}) // Ajout du filtre de vue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    // On ne met pas QuestionView.Public ici pour éviter une boucle infinie JSON (car la session chargerait la question qui rechargerait la session...)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    @JsonView({SessionView.AvecQuestions.class, QuestionView.Public.class}) // Ajout du filtre de vue
    private Produit produit;
}