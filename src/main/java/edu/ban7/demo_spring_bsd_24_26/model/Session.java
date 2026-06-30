package edu.ban7.demo_spring_bsd_24_26.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.view.SessionView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SessionView.Public.class)
    private Integer id;

    @JsonView(SessionView.Public.class)
    private String nom;

    @JsonView(SessionView.Public.class)
    private int nombreJoueur;

    // Une session contient plusieurs questions.
    // CascadeType.ALL permet de sauvegarder automatiquement les questions liées lors du 'save' de la Session.
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(SessionView.AvecQuestions.class)
    private List<Question> questions = new ArrayList<>();
}