package com.uninabiogarden.oobd68.dao;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Coltura;

import java.sql.*;
import java.util.Date; // Necessario per il campo Date

public class ColturaDAO {

    // Costanti connessione
    private static final String URL = "jdbc:mysql://localhost:3306/unina_biogarden";
    private static final String USER = "root";
    private static final String PASS = "password";

    // AGGIUNTA: Riferimento al com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    private Controller controller;

    // AGGIUNTA: Costruttore che accetta il com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    public ColturaDAO(Controller controller) {
        this.controller = controller;
    }

    // =================================================================================
    // METODO: getStagioneMigliore
    // =================================================================================
    public String getStagioneMigliore(int idColtura) {
        String stagioneIdeale = "Non specificata";

        String sql = "SELECT tipo_ortaggio, tempo_maturazione FROM coltura WHERE id_coltura = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idColtura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 1. Recupero i dati dal database
                    String tipoOrtaggio = rs.getString("tipo_ortaggio");

                    // 2. Recupero la data
                    java.sql.Date dataMaturazioneSql = rs.getDate("tempo_maturazione");
                    Date tempoMaturazione = (dataMaturazioneSql != null) ? new Date(dataMaturazioneSql.getTime()) : null;

                    // 3. Creiamo l'oggetto com.uninabiogarden.oobd68.entity.Coltura temporaneo per usare la sua logica interna
                    // Nota: Assicurati che il costruttore di com.uninabiogarden.oobd68.entity.Coltura accetti (int, Date, String, double)
                    Coltura c = new Coltura(idColtura, tempoMaturazione, tipoOrtaggio, 0.0);

                    stagioneIdeale = c.getStagioneMigliore();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Errore nel recupero dati";
        }

        return stagioneIdeale;
    }
}