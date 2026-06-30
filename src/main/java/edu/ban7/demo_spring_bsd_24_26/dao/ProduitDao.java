package edu.ban7.demo_spring_bsd_24_26.dao;

import edu.ban7.demo_spring_bsd_24_26.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitDao extends JpaRepository<Produit, Integer> {

    // Cette requête SQL native te permettra de récupérer 10 produits au hasard pour une session
    @Query(value = "SELECT * FROM produit ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Produit> findTenRandomProducts();
}
