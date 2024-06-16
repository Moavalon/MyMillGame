package com.muehle;

import javafx.scene.image.ImageView;

public class Knotenpunkt {

    private String spielerFarbe;
    private boolean besetzt;
    private ImageView imageView;


    //Knotenpunkt-Konstruktor welchen den Namen A1, A2 ... ect bestimmt. Farbe ist anfangs ohne und der Knotenpunkt
    //ist nicht belegt

    public Knotenpunkt(ImageView imageView) {
        this.imageView = imageView;
        this.spielerFarbe = "ohne";
        this.besetzt=false;
    }

    public void setSpielerFarbeUndBesetzt(String neueFarbe) {
        if (neueFarbe != null && !neueFarbe.isEmpty()) {

            // Aktualisieren der Farbe mit der neuen Farbe
            this.spielerFarbe = neueFarbe;


            // Setzen des Besetztheitsstatus auf true
            this.besetzt = true;
        } else {
            // Setzen des Besetztheitsstatus auf false und Zurücksetzen der Farbe auf "ohne"
            this.besetzt = false;
            this.spielerFarbe = "ohne";
        }
    }

    // Gibt die Spielerfarbe dieses Knotenpunkts zurück
    public String getSpielerFarbe() {
        return spielerFarbe;
    }

    // Überprüft, ob das ImageView dieses Knotenpunkts besetzt ist
    public boolean isImageViewBesetzt(ImageView imageView) {
        return imageView.getImage() != null;
    }

    // Setzt den Besetztheitsstatus dieses Knotenpunkts
    public void setBesetzt(boolean besetzt) {
        this.besetzt = besetzt;
    }

    // Gibt das ImageView dieses Knotenpunkts zurück
    public ImageView getImageView() {
        return imageView;
    }

    // Methode um den Namen des Knotenpunkts abzurufen
    public String getName() {
        // Hier kannst du den Namen aus dem ImageView extrahieren oder eine andere Implementierung wählen
        return imageView.getId();
    }

}
