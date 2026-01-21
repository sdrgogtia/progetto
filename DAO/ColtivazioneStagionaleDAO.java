package com.uninabiogarden.oobd68.dao;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Attivita;

import java.sql.*;

public class ColtivazioneStagionaleDAO {

    // Costanti connessione
    private static final String URL = "jdbc:mysql://localhost:3306/unina_biogarden";
    private static final String USER = "root";
    private static final String PASS = "password";

    // AGGIUNTA: Riferimento al com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    private Controller controller;

    // AGGIUNTA: Costruttore che accetta il com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    public ColtivazioneStagionaleDAO(Controller controller) {
        this.controller = controller;
    }

    // =================================================================================
    // METODO: aggiungiAttivita
    // =================================================================================
    public void aggiungiAttivita(int idColtivazione, Attivita nuovaAttivita) {

        String sql = "INSERT INTO attivita (" +
                "id_attivita, inizio_sem, fine_sem, inizio_racc, fine_raccol, " +
                "inizio_irrig, fine_irrig, id_semina, id_raccolta, id_irrigazione, " +
                "soluzione, tipo_stato, id_curacoltura, id_coltivazione) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. DATI GENERICI ATTIVITÀ (Corretto in getIdAttivita)
            ps.setInt(1, nuovaAttivita.getidAttivita());

            // 2. DATE SEMINA
            if (nuovaAttivita.getinizioSemina() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(nuovaAttivita.getinizioSemina()));
            } else {
                ps.setNull(2, Types.TIMESTAMP);
            }

            if (nuovaAttivita.getfineSemina() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(nuovaAttivita.getfineSemina()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }

            // 3. DATE RACCOLTA
            if (nuovaAttivita.getinizioRaccolta() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(nuovaAttivita.getinizioRaccolta()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            if (nuovaAttivita.getfineRaccolta() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(nuovaAttivita.getfineRaccolta()));
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            // 4. DATE IRRIGAZIONE
            if (nuovaAttivita.getinizioIrrigazione() != null) {
                ps.setTimestamp(6, Timestamp.valueOf(nuovaAttivita.getinizioIrrigazione()));
            } else {
                ps.setNull(6, Types.TIMESTAMP);
            }

            if (nuovaAttivita.getfineIrrigazione() != null) {
                ps.setTimestamp(7, Timestamp.valueOf(nuovaAttivita.getfineIrrigazione()));
            } else {
                ps.setNull(7, Types.TIMESTAMP);
            }

            // 5. CAMPI SPECIFICI UML (Impostati a NULL se mancano nella classe)
            ps.setNull(8, Types.INTEGER);  // id_semina
            ps.setNull(9, Types.INTEGER);  // id_raccolta
            ps.setNull(10, Types.INTEGER); // id_irrigazione
            ps.setNull(11, Types.INTEGER); // soluzione

            // 6. TIPO STATO
            if (nuovaAttivita.getTipoStato() != null) {
                ps.setString(12, nuovaAttivita.getTipoStato().name());
            } else {
                ps.setNull(12, Types.VARCHAR);
            }

            // 7. ALTRO CAMPO UML
            ps.setNull(13, Types.INTEGER); // id_curacoltura

            // 8. CHIAVE ESTERNA
            ps.setInt(14, idColtivazione);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}