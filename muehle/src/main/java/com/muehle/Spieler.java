package com.muehle;

public class Spieler {
    private final String farbe;
    private int anzahlSpielsteine;
    private String spielerName;

    public Spieler(String farbe) {
        this.farbe = farbe;
        this.anzahlSpielsteine = 9;
    }

    public String getFarbe() {
        return farbe;
    }

    public int getAnzahlSpielsteine() {
        return anzahlSpielsteine;
    }

    public void setAnzahlSpielsteine() {
        this.anzahlSpielsteine -= 1;
    }


    public String getSpielerName() {
        return spielerName;
    }

    public void setSpielerName(String spielerName) {
        this.spielerName = spielerName;
    }

}

