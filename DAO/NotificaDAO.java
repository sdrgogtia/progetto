package com.uninabiogarden.oobd68.dao;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Notifica;

import java.sql.*;

public class NotificaDAO {

    // Costanti di connessione
    private static final String URL = "jdbc:mysql://localhost:3306/unina_biogarden";
    private static final String USER = "root";
    private static final String PASS = "password";

    // AGGIUNTA: Riferimento al com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller [cite: 480]
    private Controller controller;

    // AGGIUNTA: Costruttore che accetta il com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    public NotificaDAO(Controller controller) {
        this.controller = controller;
    }

    // =================================================================================
    // 1. METODO INSERISCI NOTIFICA
    // =================================================================================
    public void inserisciNotifica(Notifica nuovaNotifica) {
        // Query SQL esatta dalla documentazione [cite: 489-490]
        String sql = "INSERT INTO notifica (id_notifica, contenuto, problema, tipo_messaggio, letta) VALUES (?, ?, ?, ?, 0)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. ID com.uninabiogarden.oobd68.entity.Notifica (Corretto in getIdNotifica con la I maiuscola) [cite: 346, 486]
            ps.setInt(1, nuovaNotifica.getidNotifica());

            // 2. Contenuto [cite: 345, 486]
            ps.setString(2, nuovaNotifica.getContenuto());

            // 3. Problema
            String problema = nuovaNotifica.getProblema();
            if (problema != null) {
                ps.setString(3, problema);
            } else {
                ps.setNull(3, Types.VARCHAR);
            }

            // 4. Tipo com.uninabiogarden.oobd68.entity.Messaggio (Enum)
            if (nuovaNotifica.getTipoMessaggio() != null) {
                ps.setString(4, nuovaNotifica.getTipoMessaggio().name());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =================================================================================
    // 2. METODO CONTRASSEGNA COME LETTA
    // =================================================================================
    public void contrassegnaComeLetta(int idNotifica) {
        // Query SQL esatta dalla documentazione [cite: 500-502]
        String sql = "UPDATE notifica SET letta = 1 WHERE id_notifica = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idNotifica);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}