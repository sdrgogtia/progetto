package com.uninabiogarden.oobd68.entity;

import java.util.ArrayList;

public class Lotto {
    private ArrayList<Attivita>svolgeAttivita;
    private ArrayList<ColtivazioneStagionale>possiedeColtivazioneStagionale;
    private ArrayList<Coltura>ContieneColtura;
    private Utente utente;
    private ArrayList<Utente> collaboratori;
    private int idLotto;
    private double superficie;
    private String tipoTerreno;

    public Lotto() {
    }

    public ArrayList<Attivita> getSvolgeAttivita() {
        return svolgeAttivita;
    }

    public void addSvolgeAttivita(Attivita attivita){
        if(!this.svolgeAttivita.add(attivita)){
            this.svolgeAttivita.add(attivita);
        }
    }

    public void removeSvolgeAttivita(Attivita attivita){
        this.svolgeAttivita.remove(attivita);
    }

    public void setSvolgeAttivita(ArrayList<Attivita> svolgeAttivita) {
        this.svolgeAttivita = svolgeAttivita;
    }

    public ArrayList<ColtivazioneStagionale> getPossiedeColtivazioneStagionale() {
        return possiedeColtivazioneStagionale;
    }

    public void addPossiedeColtivazioneStagionale(ColtivazioneStagionale coltivazioneStagionale){
        if(!this.possiedeColtivazioneStagionale.contains(coltivazioneStagionale)){
            this.possiedeColtivazioneStagionale.add(coltivazioneStagionale);
        }
    }

    public void removePossiedeColtivazioneStagionale(ColtivazioneStagionale coltivazioneStagionale){
        this.possiedeColtivazioneStagionale.remove(coltivazioneStagionale);
    }

    public void setPossiedeColtivazioneStagionale(ArrayList<ColtivazioneStagionale> possiedeColtivazioneStagionale) {
        this.possiedeColtivazioneStagionale = possiedeColtivazioneStagionale;
    }

    private ArrayList<Coltura>getContieneColtura(){
        return this.ContieneColtura;
    }

    public void addContieneColtura(Coltura coltura){
        if(!this.ContieneColtura.contains(coltura)){
            this.ContieneColtura.add(coltura);
        }
    }

    public void removeContieneColtura(Coltura coltura){
        this.ContieneColtura.remove(coltura);
    }

    public void setContieneColtura(ArrayList<Coltura> contieneColtura) {
        ContieneColtura = contieneColtura;
    }

    public boolean isTerrenoAdatto(Coltura coltura) {
        String terrenoRichiesto = coltura.getTipoortaggio();
        return this.tipoTerreno.equals(terrenoRichiesto);
    }

    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente){
        this.utente=utente;
    }

    public ArrayList<Utente> getCollaboratori() {
        return this.collaboratori;
    }

    public void addCollaboratori(Utente utente) {
        if (!this.collaboratori.contains(utente)) {
            this.collaboratori.add(utente);
        }
    }

public void removecollaboratori (Utente utente) {
        this.collaboratori.remove(utente);
    }

    public void setCollaboratori(ArrayList<Utente> collaboratori) {
        this.collaboratori = collaboratori;
    }

    public int getIdLotto() {
        return idLotto;
    }

    public void setIdLotto(int idLotto) {
        idLotto = idLotto;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        superficie = superficie;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }
}
