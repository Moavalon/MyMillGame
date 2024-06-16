package com.muehle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Set;

import static com.muehle.KnotenpunktGruppenIdentifizierer.findKnotenpunktGruppen;
import static com.muehle.KnotenpunktGruppenIdentifizierer.knotenpunktGruppenMapping;
import static com.muehle.Main.knotenpunkte;
import static com.muehle.SpielLogik.entferneGegnerischenSpielstein;

public class SpielController {

    // Spieler erstellen
    public static Spieler spieler1 = new Spieler("white"); // Spieler 1
    public static Spieler spieler2 = new Spieler("black"); // Spieler 2
    private ImageView selectedImageView; // Speichert den ausgewählten Spielstein
    private Spieler aktuellerSpieler = spieler1; // Initialisierung des aktuellen Spielers
    private boolean muehleErreicht = false; // Flagge für Mühle-Erreichen
    private boolean spielsteinEntfernt = false; // Flagge für Spielstein-Entfernung
    private boolean alleSteineGesetzt = false; // Flagge für gesetzte Steine
    private Knotenpunkt knotenpunkt; // Aktueller Knotenpunkt
    private MarkerManager markerManager; // Marker-Manager für GUI-Markierungen
    @FXML
    private ImageView A1, A2, A3, A4, A5, A6, A7, A8,
            M1, M2, M3, M4, M5, M6, M7, M8,
            I1, I2, I3, I4, I5, I6, I7, I8,
            B1, B2, B3, B4, B5, B6, B7, B8, B9,
            W1, W2, W3, W4, W5, W6, W7, W8, W9; // Knotenpunkt-Bilder
    @FXML
    private Label muehle; // Label für Mühlenanzeige
    @FXML
    private Pane pane; // Pane für GUI-Elemente

    // Initialisierungsmethode
    public void initialize() {
        markerManager = new MarkerManager(pane);
    }

    // Wechseln des aktuellen Spielers
    private void aktualisiereAktuellenSpieler() {
        aktuellerSpieler = (aktuellerSpieler == spieler1) ? spieler2 : spieler1;
    }

    // Spielstart-Methode
    public void spielBeginn() {
        markerManager.markiereFreieKnotenpunkte(new ArrayList<>(knotenpunkte.values()));
    }

    // Aktualisieren des Mühle-Labels
    private void aktualisiereMuehleLabel() {
        muehle.setText(aktuellerSpieler == spieler1 ? spieler1.getSpielerName() : spieler2.getSpielerName());
    }

    // Setzt den Text des Mühle-Labels
    public void setMuehleLabelText(String text) {
        muehle.setText(text);
    }

    // Wechseln des Spielers basierend auf der Position
    private void wechsleSpieler(String position) {
        if (position.startsWith("W") && !muehleErreicht) {
            aktuellerSpieler = spieler2;
        } else {
            aktuellerSpieler = spieler1;
            muehleErreicht = false;
        }
    }

    // Setzt einen Spielstein zu Beginn des Spiels
    private void setzeSteinZuBeginn(String id, ImageView clickedKnotenpunkt) {
        String farbe = KnotenpunkteErsteller.getFarbe(id);
        knotenpunkt.setSpielerFarbeUndBesetzt(farbe);
        clickedKnotenpunkt.setImage(knotenpunkte.get(id).getImageView().getImage());
        knotenpunkte.get(id).getImageView().setImage(null);
        knotenpunkte.get(id).setBesetzt(false);
        aktualisiereKnotenpunktFarbe("ohne", id);
    }

    // Event-Handler für Knotenpunkt-Klicks zu Beginn des Spiels
    @FXML
    private void onKnotenpunktDirectClick(MouseEvent event) {
        if (alleSteineGesetzt) {
            onImageViewClicked(event);
            return;
        }
        ImageView clickedKnotenpunkt = (ImageView) event.getSource();
        String knotenpunktId = clickedKnotenpunkt.getId();
        knotenpunkt = knotenpunkte.get(knotenpunktId);

        String[] startBereich = {"W1", "B1", "W2", "B2", "W3", "B3", "W4", "B4", "W5", "B5", "W6", "B6", "W7", "B7", "W8", "B8", "W9", "B9"};

        for (String position : startBereich) {
            markerManager.markiereFreieKnotenpunkte(new ArrayList<>(knotenpunkte.values()));
            if (knotenpunkt.getSpielerFarbe().equals("ohne")) {
                setzeSteinZuBeginn(position, clickedKnotenpunkt);
                markerManager.entferneMarkierungen();
                String aktuelleFarbe = aktuellerSpieler.getFarbe();
                wechsleSpieler(position);
                aktualisiereMuehleLabel();
            }

            if (position.equals("B9") && knotenpunkte.get("B9").getSpielerFarbe().equals("ohne")) {
                alleSteineGesetzt = true;
                break;
            }
        }
    }



    @FXML
    private void onImageViewClicked(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();

        if (selectedImageView != null) {
            onKnotenpunktClicked(clickedImageView);
        } else {
            String spielerFarbe = KnotenpunkteErsteller.getFarbe(clickedImageView.getId());
            if ((aktuellerSpieler == spieler1 && spielerFarbe.equals("white")) ||
                    (aktuellerSpieler == spieler2 && spielerFarbe.equals("black"))) {
                selectedImageView = clickedImageView;
                // Markiere nur die freien Knotenpunkte neben dem aktuellen Spieler
                markerManager.markiereFreieKnotenpunkteNebenan(knotenpunkte.get(clickedImageView.getId()), new ArrayList<>(knotenpunkte.values()));
            }
        }

        if (!muehleErreicht) {
            muehle.setText(aktuellerSpieler == spieler1 ? spieler1.getSpielerName() : spieler2.getSpielerName());
        }
        muehleErreicht = false;
    }

    // Behandlung von Knotenpunkt-Klicks
    private void onKnotenpunktClicked(ImageView clickedKnotenpunkt) {
        String knotenpunktId = clickedKnotenpunkt.getId();
        knotenpunkt = knotenpunkte.get(knotenpunktId);
        if (selectedImageView != null) {
            Knotenpunkt selectedKnotenpunkt = knotenpunkte.get(selectedImageView.getId());
            // Überprüfen Sie, ob der ausgewählte Knotenpunkt neben dem ursprünglich ausgewählten Knotenpunkt liegt
            if (MarkerManager.sindBenachbart(selectedKnotenpunkt, knotenpunkt)) {
                if (!knotenpunkt.isImageViewBesetzt(knotenpunkt.getImageView())) {
                    String selectedFarbe = KnotenpunkteErsteller.getFarbe(selectedImageView.getId());
                    clickedKnotenpunkt.setImage(selectedImageView.getImage());
                    knotenpunkt.setBesetzt(true);
                    aktualisiereKnotenpunktFarbe(selectedFarbe, knotenpunktId);
                    selectedKnotenpunkt.setSpielerFarbeUndBesetzt("ohne");
                    findKnotenpunktGruppen(knotenpunktId);
                    markerManager.entferneMarkierungen();
                    if (SpielLogik.pruefeAufMuehle(knotenpunktId, selectedFarbe, knotenpunktGruppenMapping)) {
                        muehleGeschafft();
                    }
                    selectedImageView.setImage(null);
                    selectedImageView = null;
                    aktualisiereAktuellenSpieler();
                } else {
                    markerManager.entferneMarkierungen();
                    selectedImageView = null;
                }
            } else {
                markerManager.entferneMarkierungen();
                selectedImageView = null;
            }
        } else {
            selectedImageView = null;
        }
    }



    // Aktualisieren der Farbe des Knotenpunkts
    void aktualisiereKnotenpunktFarbe(String farbe, String knotenpunktId) {
        Knotenpunkt knotenpunkt = knotenpunkte.get(knotenpunktId);
        knotenpunkt.setSpielerFarbeUndBesetzt(farbe);
    }

    // Behandlung, wenn eine Mühle gebildet wurde
    public void muehleGeschafft() {
        muehleErreicht = true;
        muehle.setText("Mühle!");
        spielsteinEntfernt = false;
        Spieler gegnerSpieler = (aktuellerSpieler == spieler1) ? spieler2 : spieler1;
        markerManager.markiereBesetzteKnotenpunkte(new ArrayList<>(knotenpunkte.values()), aktuellerSpieler, gegnerSpieler);
        entferneGegnerischenSpielstein(knotenpunkte, aktuellerSpieler, gegnerSpieler, markerManager, this);
        gegnerSpieler.setAnzahlSpielsteine();
        // Überprüfung, ob der gegnerische Spieler weniger als 3 Spielsteine hat und das Spiel beendet
        if (gegnerSpieler.getAnzahlSpielsteine() < 3) {
            // Überprüfung, ob der gegnerische Spieler weniger als 3 Spielsteine hat und das Spiel beendet
            if (gegnerSpieler.getAnzahlSpielsteine() < 3) {
                String gewinner = aktuellerSpieler == spieler1 ? spieler1.getSpielerName() : spieler2.getSpielerName();
                Main.controller.setGewinnerLabel(gewinner);
                Main.gameStage.close();
            }
        }
    }





    // Getter und Setter für die Spielstein-Entfernungs-Flagge
    public boolean isSpielsteinEntfernt() {
        return spielsteinEntfernt;
    }

    public void setSpielsteinEntfernt(boolean spielsteinEntfernt) {
        this.spielsteinEntfernt = spielsteinEntfernt;
    }

}
