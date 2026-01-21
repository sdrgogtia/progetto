package com.uninabiogarden.oobd68.entity;

import com.uninabiogarden.oobd68.dao.UtenteDAO;

import java.util.ArrayList;
import java.util.List;

public class Utente {
    private ArrayList<Notifica>riceveNotifica;
    private ArrayList<Attivita> pianificaAttivita;
    private ArrayList<Attivita>svolgeAttivita;
    private ArrayList<Lotto> possiedeLotti;
    private ArrayList<Lotto>collaboraLotti;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private int idUtente;
    private Persona tipoPersona;

    public Utente(String nome, String cognome, String username, String password, Persona tipoPersona) {
        this.riceveNotifica=new ArrayList<>();
        this.pianificaAttivita=new ArrayList<>();
        this.svolgeAttivita=new ArrayList<>();
        this.possiedeLotti=new ArrayList<>();
        this.collaboraLotti=new ArrayList<>();
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.idUtente = idUtente;
        this.tipoPersona = tipoPersona;

    }

    public ArrayList<Notifica>getRiceveNotifica(){
        return this.riceveNotifica;
    }

    public void addRiceveNotifica(Notifica notifica){
        if (!this.riceveNotifica.contains(notifica)){
            this.riceveNotifica.add(notifica);
        }
    }

    public void removeRiceveNotifica(Notifica notifica){
        this.riceveNotifica.remove (notifica);
    }

    public void setRiceveNotifica(ArrayList<Notifica> riceveNotifica) {
        this.riceveNotifica = riceveNotifica;
    }

    public ArrayList<Attivita> getPianificAttivita(){
        return this.pianificaAttivita;
    }

    public void addPianificaAttivita(Attivita attivita){
        if (!this.pianificaAttivita.contains(attivita)){
            this.pianificaAttivita.add(attivita);
        }
    }

    public void removePianificaAttivita(Utente utente){
        this.pianificaAttivita.remove(utente);
    }

    public void setPianificaAttivita(ArrayList<Attivita>pianificaAttivita) {
        this.pianificaAttivita = pianificaAttivita;
    }

    public ArrayList<Attivita>getSvolgeAttivita(){
        return this.svolgeAttivita;
    }

    public void addSvolgeAttivita(Attivita attivita) {
        if (!this.svolgeAttivita.contains(attivita)){
            this.svolgeAttivita.add(attivita);
        }
    }

    public void removeSvolgeAttivita(Attivita attivita) {
        this.svolgeAttivita.remove(attivita);
    }

    public void setSvolgeAttivita(ArrayList<Attivita>svolgeAttivita){
        this.svolgeAttivita=svolgeAttivita;
    }

    public ArrayList<Lotto> getpossiedeLotti() {
        return this.possiedeLotti;
    }

        public void addpossiedeLotti(Lotto lotto) {
    if (!this.possiedeLotti.contains(lotto)) {
        this.possiedeLotti.add(lotto);
    }
}
public void removepossiedelotti (Lotto lotto){
        this.possiedeLotti.remove(lotto);
}

public void setPossiedeLotti(ArrayList<Lotto>collaboraLotti){
        this.collaboraLotti=collaboraLotti;
}

    public ArrayList<Lotto> getcollaboraLotti() {
        return this.collaboraLotti;
    }

public void addcollaboraLotti (Lotto lotto){
        if (!this.collaboraLotti.contains(lotto));
    {
        this.collaboraLotti.add(lotto);
    }
}

public void removecollaboraLotti(Lotto lotto){
        this.collaboraLotti.remove(lotto);
}

public void setCollaboraLotti(ArrayList<Lotto>collaboraLotti){
        this.collaboraLotti=collaboraLotti;
}

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        UtenteDAO dao = new UtenteDAO();
        Utente utenteTrovato = dao.login(username, password);

        if (utenteTrovato != null) {

            this.idUtente = utenteTrovato.getIdUtente();
            this.nome = utenteTrovato.getNome();
            this.cognome = utenteTrovato.getCognome();
            return true;
        } else {
            return false;
        }
    }

    public boolean creaProgettoStagionale(Lotto lotto, List<Coltura> listaColture) {
        if (lotto == null) {
            throw new IllegalArgumentException("Lotto non valido");
        }
        if (listaColture == null || listaColture.isEmpty()) {
            throw new IllegalArgumentException("Devi selezionare almeno una coltura");
        }

        UtenteDAO dao = new UtenteDAO();

        return dao.creaProgettoStagionale(lotto, listaColture);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        idUtente = idUtente;
    }

    public Persona getTipoPersona() {
        return tipoPersona;
    }

    public void settipoPersona(Persona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}

