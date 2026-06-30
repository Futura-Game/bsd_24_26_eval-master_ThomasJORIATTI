package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.ReponseView;
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
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ReponseView.Public.class)
    private Integer id;

    // Le prix proposé par le joueur
    @JsonView(ReponseView.Public.class)
    private int prixPropose;

    // Le score calculé pour cette réponse (ex: nombre de points perdus ou écart)
    @JsonView(ReponseView.Public.class)
    private double scoreEcart;

    // La réponse est donnée par un utilisateur spécifique (Relation ManyToOne)
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @JsonView(ReponseView.Public.class)
    private AppUser appUser;

    // La réponse est liée à une question spécifique (Relation ManyToOne)
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}