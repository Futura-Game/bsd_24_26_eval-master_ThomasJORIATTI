package edu.ban7.demo_spring_bsd_24_26.dao;

import edu.ban7.demo_spring_bsd_24_26.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseDao extends JpaRepository<Reponse, Integer> {

    // Requête pour récupérer toutes les réponses d'un utilisateur au sein d'une session donnée
    @Query("SELECT r FROM Reponse r WHERE r.appUser.id = :userId AND r.question.session.id = :sessionId")
    List<Reponse> findByUserIdAndSessionId(@Param("userId") int userId, @Param("sessionId") int sessionId);
}