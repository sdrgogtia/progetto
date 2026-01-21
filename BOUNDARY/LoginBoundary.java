package com.uninabiogarden.oobd68.boundary;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Utente;

import javax.swing.*;
import java.awt.*;

public class LoginBoundary extends JFrame {

    private Controller controller;
    private JTextField userField;
    private JPasswordField passField;

    // COSTRUTTORE VUOTO
    // Fondamentale perché è la prima finestra chiamata dal Main senza parametri
    public LoginBoundary() {
        super("Login - Unina BioGarden");

        // Qui nasce il Controller che gestirà tutta la sessione
        this.controller = new Controller();

        // Impostazioni finestra
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra nello schermo
        setLayout(new GridLayout(4, 1, 10, 10)); // Layout a 4 righe

        // --- 1. Titolo ---
        JLabel titleLabel = new JLabel("ACCESSO SISTEMA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel);

        // --- 2. Campo Username ---
        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("Username:"));
        userField = new JTextField(15);
        userPanel.add(userField);
        add(userPanel);

        // --- 3. Campo Password ---
        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("Password:"));
        passField = new JPasswordField(15);
        passPanel.add(passField);
        add(passPanel);

        // --- 4. Bottone Accedi ---
        JPanel btnPanel = new JPanel();
        JButton btnLogin = new JButton("ACCEDI");
        btnPanel.add(btnLogin);
        add(btnPanel);

        // Azione al click del bottone
        btnLogin.addActionListener(e -> eseguiLogin());
    }

    // Metodo privato per gestire la logica di login
    private void eseguiLogin() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        // Chiamata al metodo del Controller (come da tua documentazione)
        Utente utente = controller.PublicUtenteNome(user, pass);

        if (utente != null) {
            // LOGIN RIUSCITO
            JOptionPane.showMessageDialog(this, "Benvenuto " + utente.getNome());

            // Apre il Menu Principale passando il controller (così manteniamo i dati)
            new MenuPrincipaleBoundary(controller).setVisible(true);

            // Chiude questa finestra di login
            this.dispose();
        } else {
            // LOGIN FALLITO
            JOptionPane.showMessageDialog(this, "Credenziali Errate! Riprova.", "Errore Accesso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
