package com.uninabiogarden.oobd68.entity;

import java.util.ArrayList;

public class Notifica {
    private ArrayList<Attivita>generataDaAttivita;
    private ArrayList<Coltura>riguardaColtura;
    private ArrayList<Utente>mandate;
    private String contenuto;
    private String problema;
    private int idNotifica;
    private Messaggio tipoMessaggio;

    public Notifica(String contenuto, String problema, int idNotifica, Messaggio tipoMessaggio) {
        this.generataDaAttivita=new ArrayList<>();
        this.riguardaColtura=new ArrayList<>();
        this.mandate=new ArrayList<>();
        this.contenuto = contenuto;
        this.problema = problema;
        this.idNotifica = idNotifica;
        this.tipoMessaggio = tipoMessaggio;
    }
    public Notifica(){
    }

    public ArrayList<Attivita>getGenerataDaAttivita() {
        return generataDaAttivita;
    }

    public void addGenerataDaAttivita(Attivita attivita){
        if(!this.generataDaAttivita.add(attivita)){
            this.generataDaAttivita.add(attivita);
        }
    }

    public void removeGenerataDaAttivita(Attivita attivita){
        this.generataDaAttivita.add(attivita);
    }

    public void setGenerataDaAttivita(ArrayList<Attivita>generataDaAttivita) {
        this.generataDaAttivita = generataDaAttivita;
    }

    public ArrayList<Coltura> getRiguardaColtura() {
        return riguardaColtura;
    }

    public void addRiguardaColtura(Coltura coltura){
        if(!this.riguardaColtura.contains(coltura)){
            this.riguardaColtura.remove(coltura);
        }
    }

    public void removeRiguardaColtura(Coltura coltura){
        this.riguardaColtura.remove(coltura);
    }

    public void setRiguardaColtura(ArrayList<Coltura> riguardaColtura) {
        this.riguardaColtura = riguardaColtura;
    }

    public ArrayList<Utente>getMandate(){
        return this.mandate;
    }

    public void addMandate(Utente utente){
        if (!this.mandate.contains(utente)){
            this.mandate.add(utente);
        }
    }

    public void removeMandate(Utente utente){
        this.mandate.remove(utente);
    }

    public void setMandate(ArrayList<Utente> mandate) {
        this.mandate = mandate;
    }

    public void contrassegnaComeLetta(boolean contenuto){
        this.contenuto= String.valueOf(true);
    }

    public void inserisciNotifica() {
        if (this.contenuto == null || this.contenuto.isEmpty()) {
            System.out.println("Errore Impossibile inserire una notifica vuota");
            return;
        }
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public int getidNotifica() {
        return idNotifica;
    }

    public void setidNotifica(int idnotifica) {
        this.idNotifica = idnotifica;
    }

    public Messaggio getTipoMessaggio() {
        return tipoMessaggio;
    }

    public void setTipoMessaggio(Messaggio tipoMessaggio) {
        this.tipoMessaggio = tipoMessaggio;
    }
}


