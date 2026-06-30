package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.QuestionDao;
import edu.ban7.demo_spring_bsd_24_26.dao.ReponseDao;
import edu.ban7.demo_spring_bsd_24_26.model.Question;
import edu.ban7.demo_spring_bsd_24_26.model.Reponse;
import edu.ban7.demo_spring_bsd_24_26.view.ReponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired
    private ReponseDao reponseDao;

    @Autowired
    private QuestionDao questionDao;

    @PostMapping
    @JsonView(ReponseView.Public.class)
    public ResponseEntity<Reponse> validerReponse(@RequestBody Reponse nouvelleReponse) {

        // 1. On vérifie que la question existe bien pour récupérer le vrai prix du produit
        if (nouvelleReponse.getQuestion() == null || nouvelleReponse.getQuestion().getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Question> oQuestion = questionDao.findById(nouvelleReponse.getQuestion().getId());
        if (oQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Question question = oQuestion.get();
        double prixReel = question.getProduit().getPrix();
        int prixPropose = nouvelleReponse.getPrixPropose();

        // 2. Calcul de l'écart (Logique du diagramme : chaque écart de prix coûte 1 point)
        // Math.abs permet d'avoir toujours un écart positif, peu importe si le joueur a dit trop haut ou trop bas
        double ecart = Math.abs(prixReel - prixPropose);
        nouvelleReponse.setScoreEcart(ecart);

        // Liaison correcte de l'objet complet Question pour la persistance
        nouvelleReponse.setQuestion(question);

        // 3. Sauvegarde en BDD
        reponseDao.save(nouvelleReponse);

        return new ResponseEntity<>(nouvelleReponse, HttpStatus.CREATED);
    }
}