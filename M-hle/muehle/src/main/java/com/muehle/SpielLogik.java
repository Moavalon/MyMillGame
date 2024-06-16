package com.muehle;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class SpielLogik {

    // Überprüft, ob alle Knotenpunkte die gleiche Farbe haben.
    public static boolean sindAlleDreiGleichfarbig(Knotenpunkt[] knotenpunkte, String farbe) {
        for (Knotenpunkt knotenpunkt : knotenpunkte) {
            if (knotenpunkt == null ||
                    !knotenpunkt.isImageViewBesetzt(knotenpunkt.getImageView()) ||
                    !knotenpunkt.getSpielerFarbe().equals(farbe)) {
                return false;
            }
        }
        return true;
    }

    // Überprüft, ob eine Mühle gebildet wurde.
    public static boolean pruefeAufMuehle(String knotenpunktId, String farbe, Map<String, Knotenpunkt[][]> knotenpunktGruppenMapping) {
        // Überprüfen, ob die Farbe entweder "white" oder "black" ist
        if (!farbe.equals("white") && !farbe.equals("black")) {
            return false;
        }

        // Überprüfen, ob der Knotenpunkt in einer Mühle ist
        Knotenpunkt[][] moeglicheGruppen = knotenpunktGruppenMapping.get(knotenpunktId);
        for (Knotenpunkt[] gruppe : moeglicheGruppen) {
            if (sindAlleDreiGleichfarbig(gruppe, farbe)) {
                return true;
            }
        }
        return false;
    }

    public static void entferneGegnerischenSpielstein(Map<String, Knotenpunkt> knotenpunkte, Spieler aktuellerSpieler, Spieler gegnerSpieler, MarkerManager markerManager, SpielController controller) {
        boolean spielsteinEntfernt = false;
        for (Knotenpunkt currentKnotenpunkt : knotenpunkte.values()) {
            String knotenpunktFarbe = currentKnotenpunkt.getSpielerFarbe();
            String knotenpunktId = currentKnotenpunkt.getName();
            if (!spielsteinEntfernt && !knotenpunktFarbe.equals("ohne") && !knotenpunktId.startsWith("W") && !knotenpunktId.startsWith("B")) {
                ImageView imageView = currentKnotenpunkt.getImageView();
                registerSpielsteinEntfernungHandler(imageView, currentKnotenpunkt, aktuellerSpieler, gegnerSpieler, markerManager, controller);
            }
        }
    }

    private static void registerSpielsteinEntfernungHandler(ImageView imageView, Knotenpunkt currentKnotenpunkt, Spieler aktuellerSpieler, Spieler gegnerSpieler, MarkerManager markerManager, SpielController controller) {
        EventHandler<MouseEvent> handler = event -> {
            if (!controller.isSpielsteinEntfernt()) {
                // Entfernt den Spielstein und aktualisiert die Farbe des Knotenpunkts
                currentKnotenpunkt.setSpielerFarbeUndBesetzt("ohne");
                imageView.setImage(null);
                markerManager.entferneMarkierungen();
                controller.aktualisiereKnotenpunktFarbe("ohne", currentKnotenpunkt.getName());
                currentKnotenpunkt.setBesetzt(false);
                controller.setSpielsteinEntfernt(true);
            }
        };
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }


}