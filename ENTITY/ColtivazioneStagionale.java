package com.uninabiogarden.oobd68.entity;

import java.util.ArrayList;

public class ColtivazioneStagionale {
    private ArrayList<Attivita>attivitaSvolte;
    private ArrayList<Lotto>possedutaInLotto;
    private ArrayList<Coltura>GestitaInColtura;
    private double superficie;
    private int idcoltivazione;
    private String tipostagione;

    public ColtivazioneStagionale(double superficie, int idcoltivazione, String tipostagione) {
        this.attivitaSvolte=new ArrayList<>();
        this.possedutaInLotto=new ArrayList<>();
        this.GestitaInColtura=new ArrayList<>();
        this.superficie = superficie;
        this.idcoltivazione = idcoltivazione;
        this.tipostagione = tipostagione;
    }

    public ArrayList<Attivita> getAttivitaSvolte() {
        return attivitaSvolte;
    }

    public void addAttivitaSvolte(Attivita attivita){
        if(!this.attivitaSvolte.add(attivita)){
            this.attivitaSvolte.add(attivita);
        }
    }

    public void removeAttivitaSvolte(Attivita attivita){
        this.attivitaSvolte.remove(attivita);
    }

    public void setAttivitaSvolte(ArrayList<Attivita> attivitaSvolte) {
        this.attivitaSvolte = attivitaSvolte;
    }

    public ArrayList<Lotto>getPossedutaInLotto() {
        return possedutaInLotto;
    }

    public void addPossedutaInLotto(Lotto lotto){
        if(!this.possedutaInLotto.contains(lotto)){
            this.possedutaInLotto.remove(lotto);
        }
    }

    public void removePossedutaInLotto(Lotto lotto){
        this.possedutaInLotto.remove(lotto);
    }

    public void setPossedutaInLotto(ArrayList<Lotto>possedutaInLotto) {
        this.possedutaInLotto = possedutaInLotto;
    }

    public ArrayList<Coltura> getGestitaInColtura() {
        return GestitaInColtura;
    }

    public void addGestitaInColtura(Coltura coltura){
        if (!this.GestitaInColtura.contains(coltura)){
            this.GestitaInColtura.add(coltura);
        }
    }

    public void removeGestitaInColtura(Coltura coltura){
        this.GestitaInColtura.remove(coltura);
    }

    public void setGestitaInColtura(ArrayList<Coltura> gestitaInColtura) {
        GestitaInColtura = gestitaInColtura;
    }

    public void aggiungiAttivita(Attivita nuovaAttivita) {
        if (nuovaAttivita != null) {
            this.attivitaSvolte.add(nuovaAttivita);
        }
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public int getIdcoltivazione() {
        return idcoltivazione;
    }

    public void setIdcoltivazione(int idcoltivazione) {
        this.idcoltivazione = idcoltivazione;
    }

    public String getTipostagione() {
        return tipostagione;
    }

    public void setTipostagione(String tipostagione) {
        this.tipostagione = tipostagione;
    }
}
