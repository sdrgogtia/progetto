package com.uninabiogarden.oobd68.dao;

import com.uninabiogarden.oobd68.controller.Controller;
import com.uninabiogarden.oobd68.entity.Attivita;
import com.uninabiogarden.oobd68.entity.Categoria;
import com.uninabiogarden.oobd68.entity.ReportDati;
import com.uninabiogarden.oobd68.entity.Stato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttivitaDAO {

    // Costanti connessione
    private static final String URL = "jdbc:mysql://localhost:3306/unina_biogarden";
    private static final String USER = "root";
    private static final String PASS = "password";

    // AGGIUNTA: Riferimento al com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    private Controller controller;

    // AGGIUNTA: Costruttore che accetta il com.uninabiogarden.oobd68.controller.com.uninabiogarden.oobd68.controller
    public AttivitaDAO(Controller controller) {
        this.controller = controller;
    }
    public AttivitaDAO(){
    }

    // =================================================================================
    // 1. IMPOSTA DETTAGLI RACCOLTA
    // =================================================================================
    public boolean impostaDettagliRaccolta(int idAttivita, double quantitaPrevista) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("UPDATE Attivita SET quantita_prevista = ? WHERE id_attivita = ?")) {

            ps.setDouble(1, quantitaPrevista);
            ps.setInt(2, idAttivita);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { return false; }
    }

    // =================================================================================
    // 2. REGISTRA RACCOLTA
    // =================================================================================
    public boolean registraRaccolta(int idAttivita, double quantitaEffettiva) {
        String sql = "UPDATE Attivita SET quantita_effettiva = ?, data_fine_effettiva = CURRENT_DATE, tipo_stato = 'COMPLETATO' WHERE id_attivita = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, quantitaEffettiva);
            ps.setInt(2, idAttivita);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { return false; }
    }

    // =================================================================================
    // 3. RECUPERA ATTIVITÀ PER PROGETTO
    // =================================================================================
    public List<Attivita> recuperaAttivitaPerProgetto(int idColtivazione) {
        List<Attivita> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Attivita WHERE id_coltivazione = ?")) {

            ps.setInt(1, idColtivazione);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Attivita a = new Attivita(); // Usa il costruttore vuoto

                // Corretto in setIdAttivita (CamelCase standard)
                a.setidAttivita(rs.getInt("id_attivita"));
                a.setQuantitaPrevista(rs.getDouble("quantita_prevista"));
                a.setQuantitaRaccolta(rs.getDouble("quantita_effettiva"));

                String cat = rs.getString("tipo_categoria");
                if (cat != null) a.setTipoCategoria(Categoria.valueOf(cat.toUpperCase()));

                String st = rs.getString("tipo_stato");
                if (st != null) a.setTipoStato(Stato.valueOf(st.toUpperCase()));

                Timestamp ts = rs.getTimestamp("data_inizio");
                if (ts != null) a.setinizioSemina(ts.toLocalDateTime());

                lista.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    // METODO PER REPORTISTICA
    public List<ReportDati> generaReport(int idLotto) {
        List<ReportDati> report = new ArrayList<>();
        // Query complessa che calcola le statistiche
        String sql = "SELECT c.tipo_ortaggio, COUNT(*) as Tot, AVG(a.quantita_effettiva) as Media, " +
                "MIN(a.quantita_effettiva) as Min, MAX(a.quantita_effettiva) as Max " +
                "FROM Attivita a " +
                "JOIN ColtivazioneStagionale cs ON a.id_coltivazione = cs.id_coltivazione " +
                "JOIN Coltura c ON cs.id_coltura = c.id_coltura " +
                "WHERE cs.id_lotto = ? AND a.tipo_categoria = 'RACCOLTA' AND a.tipo_stato = 'COMPLETATO' " +
                "GROUP BY c.tipo_ortaggio";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLotto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("tipo_ortaggio");
                int tot = rs.getInt("Tot");
                double media = rs.getDouble("Media");
                double min = rs.getDouble("Min");
                double max = rs.getDouble("Max");

                report.add(new ReportDati(nome, tot, media, min, max));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }


}