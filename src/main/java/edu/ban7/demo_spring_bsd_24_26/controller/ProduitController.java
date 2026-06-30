package edu.ban7.demo_spring_bsd_24_26.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ban7.demo_spring_bsd_24_26.dao.ProduitDao;
import edu.ban7.demo_spring_bsd_24_26.model.Produit;
import edu.ban7.demo_spring_bsd_24_26.security.IsAdmin;
import edu.ban7.demo_spring_bsd_24_26.view.ProduitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    private ProduitDao produitDao;

    @GetMapping("/list")
    @JsonView(ProduitView.Public.class)
    public List<Produit> getAll() {
        return produitDao.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ProduitView.Public.class)
    public ResponseEntity<Produit> get(@PathVariable int id) {
        Optional<Produit> oProduit = produitDao.findById(id);

        if (oProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oProduit.get(), HttpStatus.OK);
    }

    @PostMapping
    @IsAdmin // Seul l'admin peut ajouter un produit au catalogue
    public ResponseEntity<Produit> create(@RequestBody Produit nouveauProduit) {
        produitDao.save(nouveauProduit);
        return new ResponseEntity<>(nouveauProduit, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @IsAdmin // Seul l'admin peut supprimer un produit
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Produit> oProduit = produitDao.findById(id);

        if (oProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @IsAdmin // Seul l'admin peut modifier un produit
    public ResponseEntity<Void> update(@RequestBody Produit produit, @PathVariable int id) {
        produit.setId(id);

        Optional<Produit> oProduit = produitDao.findById(id);

        if (oProduit.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        produitDao.save(produit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}