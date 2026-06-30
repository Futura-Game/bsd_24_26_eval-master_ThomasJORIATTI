package edu.ban7.demo_spring_bsd_24_26.view;

public class SessionView {
    // Permet d'avoir une liste légère des sessions (sans charger toutes les questions et les produits d'un coup)
    public interface Public {}

    // Permet de charger la session en détail avec l'intégralité de ses questions
    public interface AvecQuestions extends Public {}
}