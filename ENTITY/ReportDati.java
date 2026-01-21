package com.uninabiogarden.oobd68.entity;

public class ReportDati {
    private String nomeColtura;
    private int numeroRaccolte;
    private double media;
    private double min;
    private double max;

    public ReportDati(String nomeColtura, int numeroRaccolte, double media, double min, double max) {
        this.nomeColtura = nomeColtura;
        this.numeroRaccolte = numeroRaccolte;
        this.media = media;
        this.min = min;
        this.max = max;
    }

    // Getter
    public String getNomeColtura() { return nomeColtura; }
    public int getNumeroRaccolte() { return numeroRaccolte; }
    public double getMedia() { return media; }
    public double getMin() { return min; }
    public double getMax() { return max; }
}
