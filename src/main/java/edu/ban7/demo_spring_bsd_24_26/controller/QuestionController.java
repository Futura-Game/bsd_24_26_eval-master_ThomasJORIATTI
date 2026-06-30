package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.QuestionDao;
import edu.ban7.demo_spring_bsd_24_26.model.Question;
import edu.ban7.demo_spring_bsd_24_26.view.QuestionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionDao questionDao;

    // Permet au joueur de récupérer les détails d'une seule question (pour afficher l'image et le nom du produit à deviner)
    @GetMapping("/{id}")
    @JsonView(QuestionView.Public.class)
    public ResponseEntity<Question> get(@PathVariable int id) {
        Optional<Question> oQuestion = questionDao.findById(id);

        if (oQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oQuestion.get(), HttpStatus.OK);
    }
}