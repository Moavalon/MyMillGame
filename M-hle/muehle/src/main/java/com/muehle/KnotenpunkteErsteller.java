// Diese Klasse erstellt Knotenpunkte für das Mühle-Spiel basierend auf einer FXML-Datei.
// Sie enthält Methoden zum Erstellen von besetzenden Feldern und Spielfiguren.
// Außerdem gibt sie die Farbe eines Knotenpunkts basierend auf seiner ID zurück.
package com.muehle;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import java.util.HashMap;
import java.util.Map;

import static com.muehle.Main.knotenpunkte;

public class KnotenpunkteErsteller {

    // Erstellt alle Knotenpunkte für das Spiel und gibt sie als Map zurück.
    public static Map<String, Knotenpunkt> erstelleKnotenpunkte(FXMLLoader fxmlLoader) {
        final Map<String, Knotenpunkt> knotenpunkte = new HashMap<>();
        erstelleBesetzendeFelder(fxmlLoader, knotenpunkte);
        erstelleSpielfiguren(fxmlLoader, knotenpunkte);
        return knotenpunkte;
    }

    // Erstellt die besetzenden Felder des Spiels und fügt sie der Knotenpunkte-Map hinzu.
    private static void erstelleBesetzendeFelder(FXMLLoader fxmlLoader, Map<String, Knotenpunkt> knotenpunkte) {
        final String[] buchstabenGruppen = new String[]{"A", "M", "I"};
        final int anzahlen = 8;

        for (String buchstabe : buchstabenGruppen) {
            for (int i = 1; i <= anzahlen; i++) {
                String name = buchstabe + i;
                ImageView imageView = (ImageView) fxmlLoader.getNamespace().get(name);
                Knotenpunkt knotenpunkt = new Knotenpunkt(imageView);
                knotenpunkt.setSpielerFarbeUndBesetzt("ohne");
                knotenpunkte.put(name, knotenpunkt);
            }
        }
    }

    // Erstellt die Spielfiguren des Spiels und fügt sie der Knotenpunkte-Map hinzu.
    private static void erstelleSpielfiguren(FXMLLoader fxmlLoader, Map<String, Knotenpunkt> knotenpunkte) {
        final String[] GruppeWB = new String[]{"W", "B"};
        final int stones = 9;

        for (String buchstabe : GruppeWB) {
            for (int i = 1; i <= stones; i++) {
                String name = buchstabe + i;
                ImageView imageView = (ImageView) fxmlLoader.getNamespace().get(name);
                Knotenpunkt knotenpunkt = new Knotenpunkt(imageView);
                knotenpunkt.setSpielerFarbeUndBesetzt(buchstabe.equals("W") ? "white" : "black");
                knotenpunkte.put(name, knotenpunkt);
            }
        }
    }

    // Gibt die Farbe eines Knotenpunkts zurück, basierend auf seiner ID.
    public static String getFarbe(String knotenpunktId) {
        Knotenpunkt knotenpunkt = knotenpunkte.get(knotenpunktId);
        if (knotenpunkt != null) {
            return knotenpunkt.getSpielerFarbe();
        } else {
            return null;
        }
    }
}
