package com.muehle;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.muehle.SpielController.spieler1;
import static com.muehle.SpielController.spieler2;

public class MarkerManager {

    private Pane pane;

    public MarkerManager(Pane pane) {
        this.pane = pane;
    }

    public void markiereFreieKnotenpunkte(List<Knotenpunkt> knotenpunkte) {
        entferneMarkierungen();

        for (Knotenpunkt knotenpunkt : knotenpunkte) {
            String knotenpunktId = knotenpunkt.getName();
            if (knotenpunkt.getSpielerFarbe().equals("ohne") && !knotenpunktId.startsWith("W") && !knotenpunktId.startsWith("B")) {
                Rectangle rectangle = createMarkierungsRectangle(knotenpunkt, Color.GREEN);
                pane.getChildren().add(rectangle);
            }
        }
    }

    public void markiereBesetzteKnotenpunkte(List<Knotenpunkt> knotenpunkte, Spieler aktuellerSpieler, Spieler gegnerSpieler) {
        entferneMarkierungen();

        for (Knotenpunkt knotenpunkt : knotenpunkte) {
            String knotenpunktFarbe = knotenpunkt.getSpielerFarbe();
            String knotenpunktId = knotenpunkt.getName();

            String gegnerFarbe = (aktuellerSpieler == spieler1) ? spieler2.getFarbe() : spieler1.getFarbe();

            if (!knotenpunktFarbe.equals("ohne") && !knotenpunktId.startsWith("W") && !knotenpunktId.startsWith("B") && knotenpunktFarbe.equals(gegnerFarbe)) {
                Rectangle rectangle = createMarkierungsRectangle(knotenpunkt, Color.BLUE);
                pane.getChildren().add(rectangle);
            }
        }
    }


    // Neue Methode: Markiert freie Knotenpunkte neben dem aktuellen Spieler
    public void markiereFreieKnotenpunkteNebenan(Knotenpunkt startKnotenpunkt, List<Knotenpunkt> knotenpunkte) {
        entferneMarkierungen();

        for (Knotenpunkt knotenpunkt : knotenpunkte) {
            // Prüfen, ob der Knotenpunkt "ohne" ist und benachbart zum aktuellen Spieler liegt
            if (knotenpunkt.getSpielerFarbe().equals("ohne") && sindBenachbart(startKnotenpunkt, knotenpunkt)) {
                Rectangle rectangle = createMarkierungsRectangle(knotenpunkt, Color.GREEN);
                pane.getChildren().add(rectangle);
            }
        }
    }

    // Überprüft, ob zwei Knotenpunkte benachbart sind
    static boolean sindBenachbart(Knotenpunkt knotenpunkt1, Knotenpunkt knotenpunkt2) {
        String knotenpunktId1 = knotenpunkt1.getName();
        String knotenpunktId2 = knotenpunkt2.getName();

        // Extrahieren Sie die Buchstaben und Indizes aus den Namen der Knotenpunkte
        String buchstabe1 = knotenpunktId1.substring(0, 1);
        int index1 = Integer.parseInt(knotenpunktId1.substring(1));
        String buchstabe2 = knotenpunktId2.substring(0, 1);
        int index2 = Integer.parseInt(knotenpunktId2.substring(1));

        // Felder, die mit "W" oder "B" beginnen, können niemals ausgewählt werden
        if (buchstabe1.equals("W") || buchstabe1.equals("B") || buchstabe2.equals("W") || buchstabe2.equals("B")) {
            return false;
        }

        // Überprüfen Sie, ob die Buchstaben gleich sind und die Indizes benachbart sind
        if (buchstabe1.equals(buchstabe2)) {
            if (index1 == 1 && (index2 == 2 || index2 == 8)) {
                return true;
            } else if (index1 == 8 && (index2 == 7 || index2 == 1)) {
                return true;
            } else if (Math.abs(index1 - index2) == 1) {
                return true;
            }
        }

        // Überprüfen Sie die zusätzlichen Regeln für die Positionen 2, 4, 6 und 8
        if ((index1 == 2 || index1 == 4 || index1 == 6 || index1 == 8) && index1 == index2) {
            // A2 kann nur M2 auswählen, nicht I2
            if (buchstabe1.equals("A") && buchstabe2.equals("I")) {
                return false;
            }
            // I2 kann nur M2 auswählen, nicht A2
            if (buchstabe1.equals("I") && buchstabe2.equals("A")) {
                return false;
            }
            return true;
        }

        return false;
    }



    public void entferneMarkierungen() {
        List<Node> zuEntfernen = new ArrayList<>();
        for (Node node : pane.getChildren()) {
            if (node instanceof Rectangle) {
                zuEntfernen.add(node);
            }
        }
        pane.getChildren().removeAll(zuEntfernen);
    }

    private Rectangle createMarkierungsRectangle(Knotenpunkt knotenpunkt, Color strokeColor) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(knotenpunkt.getImageView().getFitWidth());
        rectangle.setHeight(knotenpunkt.getImageView().getFitHeight());
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(strokeColor);
        rectangle.setStrokeWidth(2);
        rectangle.setMouseTransparent(true);
        rectangle.setLayoutX(knotenpunkt.getImageView().getLayoutX());
        rectangle.setLayoutY(knotenpunkt.getImageView().getLayoutY());
        return rectangle;
    }
}