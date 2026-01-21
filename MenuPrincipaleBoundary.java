package com.uninabiogarden.oobd68.boundary;

import com.uninabiogarden.oobd68.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipaleBoundary extends JFrame {

    private Controller controller;

    public MenuPrincipaleBoundary(Controller controller) {
        // Impostiamo il riferimento al controller
        this.controller = controller;

        // Impostazioni della finestra
        setTitle("Menu Principale - Unina BioGarden");
        setSize(400, 400); // Ho aumentato leggermente l'altezza per far entrare bene i 5 elementi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la finestra nello schermo

        // MODIFICA QUI: 5 Righe (Titolo + 3 bottoni vecchi + 1 bottone nuovo)
        setLayout(new GridLayout(5, 1, 10, 10));

        // Etichetta di Benvenuto
        JLabel lblTitolo = new JLabel("SELEZIONA UN'OPERAZIONE", SwingConstants.CENTER);
        lblTitolo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitolo);

        // --- Bottone 1: Crea Progetto ---
        JButton btnCrea = new JButton("CREA PROGETTO STAGIONALE");
        btnCrea.addActionListener(e -> {
            // Apre la finestra di creazione (Codice attivato)
            new CreaProgettoBoundary(controller).setVisible(true);
        });
        add(btnCrea);

        // --- Bottone 2: Visualizza Stato ---
        JButton btnVisualizza = new JButton("VISUALIZZA STATO PROGETTI");
        btnVisualizza.addActionListener(e -> {
            // Apre la finestra di visualizzazione (Codice attivato)
            new VisualizzaProgettoBoundary(controller).setVisible(true);
        });
        add(btnVisualizza);

        // --- Bottone 3 (NUOVO): Invia Notifica ---
        JButton btnNotifica = new JButton("INVIA NOTIFICA / SEGNALAZIONE");
        // Opzionale: diamo un colore diverso per distinguerlo
        btnNotifica.setForeground(new Color(0, 100, 0)); // Testo verde scuro
        btnNotifica.addActionListener(e -> {
            // Apre la nuova finestra di notifica
            new InserisciNotificaBoundary(controller).setVisible(true);
        });
        add(btnNotifica);

        JButton btnReport = new JButton("REPORT STATISTICO LOTTO");
        btnReport.setForeground(new Color(0, 0, 139)); // Blu scuro
        btnReport.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Inserisci ID Lotto per il report:");
            if (input != null && !input.isEmpty()) {
                try {
                    int idLotto = Integer.parseInt(input);
                    controller.visualizzaReport(idLotto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID non valido.");
                }
            }
        });
        add(btnReport);
        // --- Bottone 4: Esci ---
        JButton btnEsci = new JButton("ESCI");
        btnEsci.setBackground(new Color(255, 100, 100)); // Rosso chiaro per l'uscita
        btnEsci.addActionListener(e -> {
            System.out.println("Chiusura applicazione.");
            System.exit(0);
        });
        add(btnEsci);
    }

}
