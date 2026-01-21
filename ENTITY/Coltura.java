package com.uninabiogarden.oobd68.entity;

import java.util.ArrayList;
import java.util.Date;

public class Coltura {
    private ArrayList<ColtivazioneStagionale>gestitaColtivazioneStagionale;
    private ArrayList<Notifica>riguardataDaNotifica;
    private ArrayList<Lotto>contenuteInLotto;
    private int idcoltura;
    private Date tempoDiMaturazione;
    private String tipoOrtaggio;
    private double quantitaOrtaggio;

    public Coltura(){
    }

    public Coltura(int idcoltura, Date tempoDiMaturazione, String tipoOrtaggio, double quantitaOrtaggio) {
        this.gestitaColtivazioneStagionale=new ArrayList<>();
        this.riguardataDaNotifica=new ArrayList<>();
        this.contenuteInLotto=new ArrayList<>();
        this.idcoltura = idcoltura;
        this.tempoDiMaturazione = tempoDiMaturazione;
        this.tipoOrtaggio = tipoOrtaggio;
        this.quantitaOrtaggio = quantitaOrtaggio;
    }

    public ArrayList<ColtivazioneStagionale>getGestisceColtivazioneStagionale() {
        return gestitaColtivazioneStagionale;
    }

    public void addGestisceColtivazioneStagionale(ColtivazioneStagionale coltivazioneStagionale){
        if (!this.gestitaColtivazioneStagionale.contains(coltivazioneStagionale)){
            this.gestitaColtivazioneStagionale.add(coltivazioneStagionale);
        }
    }

    public void removeGestisceColtivazioneStagionale(ColtivazioneStagionale coltivazioneStagionale){
        this.gestitaColtivazioneStagionale.remove(coltivazioneStagionale);
    }

    public void setGestisceColtivazioneStagionale(ArrayList<ColtivazioneStagionale> gestisceColtivazioneStagionale) {
        this.gestitaColtivazioneStagionale = gestisceColtivazioneStagionale;
    }

    public ArrayList<Notifica> getRiguardataDaNotifiche() {
        return riguardataDaNotifica;
    }

    public void addRiguardataDaNotifiche(Notifica notifica){
        if (!this.riguardataDaNotifica.contains(notifica)){
            this.riguardataDaNotifica.add(notifica);
        }
    }

    public void removeRiguardataDaNotifica(Notifica notifica){
        this.riguardataDaNotifica.remove(notifica);
    }

    public void setRiguardataDaNotifiche(ArrayList<Notifica> riguardataDaNotifiche) {
        this.riguardataDaNotifica = riguardataDaNotifiche;
    }

    public ArrayList<Lotto> getContenuteInLotto() {
        return contenuteInLotto;
    }

    public void addcontenuteInLotto(Lotto lotto) {
        if (!this.contenuteInLotto.contains(lotto)) {
            this.contenuteInLotto.remove(lotto);
        }
    }

    public void removeContenuteInLotto(Lotto lotto){
        this.contenuteInLotto.remove(lotto);
    }

    public void setContenuteInLotto(ArrayList<Lotto>contenuteInLotto) {
        this.contenuteInLotto = contenuteInLotto;
    }

    public String getStagioneMigliore() {
        if( this.tipoOrtaggio==null){
            return "non specificato";
        }
        String nomeColtura = this.tipoOrtaggio.toLowerCase();

        if (nomeColtura.contains("pomodoro") || nomeColtura.contains("zucchina") || nomeColtura.contains("melanzana")) {
            return "Estate";
        }
        else if (nomeColtura.contains("cavolo") || nomeColtura.contains("broccoli") || nomeColtura.contains("spinaci")) {
            return "Inverno";
        }
        else if (nomeColtura.contains("fragola") || nomeColtura.contains("asparagi")) {
            return "Primavera";
        }
        else if (nomeColtura.contains("zucca") || nomeColtura.contains("funghi")) {
            return "Autunno";
        }
        else {
            return "Tutte le stagioni / Non specificato";
        }
    }

    public int getIdcoltura() {
        return idcoltura;
    }

    public void setIdcoltura(int idcoltura) {
        this.idcoltura = idcoltura;
    }

    public Date getTempoDiMaturazione() {
        return tempoDiMaturazione;
    }

    public void setTempoDiMaturazione(Date tempoDiMaturazione) {
        this.tempoDiMaturazione = tempoDiMaturazione;
    }

    public String getTipoortaggio() {
        return tipoOrtaggio;
    }

    public void setTipoOrtaggio(String tipoortaggio) {
        this.tipoOrtaggio = tipoortaggio;
    }

    public double getQuantitaOrtaggio() {
        return quantitaOrtaggio;
    }

    public void setQuantitaOrtaggio(double quantitaOrtaggio) {
        this.quantitaOrtaggio = quantitaOrtaggio;
    }
}
