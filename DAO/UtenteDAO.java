package com.uninabiogarden.oobd68.dao;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Coltura;
import com.uninabiogarden.oobd68.entity.Lotto;
import com.uninabiogarden.oobd68.entity.Persona;
import com.uninabiogarden.oobd68.entity.Utente;

import java.sql.*;
import java.util.List;

public class UtenteDAO {

    // Costanti connessione
    private static final String URL = "jdbc:mysql://localhost:3306/unina_biogarden";
    private static final String USER = "root";
    private static final String PASS = "password";

    // AGGIUNTA FONDAMENTALE PER TOGLIERE L'ERRORE NEL CONTROLLER
    private Controller controller;

    // COSTRUTTORE CHE ACCETTA IL CONTROLLER
    public UtenteDAO(Controller controller) {
        this.controller = controller;
    }
    public UtenteDAO(){}

    // =================================================================================
    // 1. LOGIN
    // =================================================================================
    public Utente login(String username, String password) {
        String sql = "SELECT * FROM utente WHERE username = ? AND password = ?";
        Utente utente = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_utente");
                    String nome = rs.getString("nome");
                    String cognome = rs.getString("cognome");
                    String ruoloString = rs.getString("tipo_persona");

                    Persona tipoRuolo = null;
                    if (ruoloString != null) {
                        try {
                            // Gestione Enum sicura
                            tipoRuolo = Persona.valueOf(ruoloString.toUpperCase().replace(" ", ""));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ruolo non valido nel DB: " + ruoloString);
                        }
                    }

                    utente = new Utente(nome, cognome, username, password, tipoRuolo);
                    utente.setIdUtente(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    // =================================================================================
    // 2. CREA PROGETTO STAGIONALE
    // =================================================================================
    public boolean creaProgettoStagionale(Lotto lotto, List<Coltura> listaColture) {

        if (lotto == null || listaColture == null || listaColture.isEmpty()) {
            return false;
        }

        String sql = "INSERT INTO ColtivazioneStagionale (id_lotto, id_coltura, stato, data_inizio) " +
                "VALUES (?, ?, 'PIANIFICATO', CURRENT_DATE)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Coltura c : listaColture) {
                ps.setInt(1, lotto.getIdLotto());
                ps.setInt(2, c.getIdcoltura());

                ps.addBatch();
            }

            int[] risultati = ps.executeBatch();

            return risultati.length > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}