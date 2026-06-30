package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.ProduitDao;
import edu.ban7.demo_spring_bsd_24_26.dao.SessionDao;
import edu.ban7.demo_spring_bsd_24_26.model.Produit;
import edu.ban7.demo_spring_bsd_24_26.model.Question;
import edu.ban7.demo_spring_bsd_24_26.model.Session;
import edu.ban7.demo_spring_bsd_24_26.security.IsAdmin;
import edu.ban7.demo_spring_bsd_24_26.view.SessionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private ProduitDao produitDao;

    @GetMapping("/list")
    @JsonView(SessionView.Public.class)
    public List<Session> getAll() {
        return sessionDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(SessionView.AvecQuestions.class) // Renvoie la session ET ses 10 questions
    public ResponseEntity<Session> get(@PathVariable int id) {
        Optional<Session> oSession = sessionDao.findById(id);

        if (oSession.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oSession.get(), HttpStatus.OK);
    }

    @PostMapping
    @IsAdmin // Seul l'admin peut initialiser une session de jeu
    public ResponseEntity<Session> create(@RequestBody Session nouvelleSession) {

        // 1. Récupération de 10 produits aléatoires depuis le catalogue
        List<Produit> produitsAleatoires = produitDao.findTenRandomProducts();

        // Sécurité si le catalogue n'a pas encore assez de produits
        if (produitsAleatoires.size() < 10) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Génération automatique des 10 questions pour la session
        for (Produit produit : produitsAleatoires) {
            Question question = new Question();
            question.setSession(nouvelleSession);
            question.setProduit(produit);

            nouvelleSession.getQuestions().add(question);
        }

        // 3. Sauvegarde de la session (et des questions grâce au CascadeType.ALL)
        sessionDao.save(nouvelleSession);

        return new ResponseEntity<>(nouvelleSession, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @IsAdmin
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Session> oSession = sessionDao.findById(id);

        if (oSession.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}