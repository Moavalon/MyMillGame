package com.muehle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KnotenpunktGruppenIdentifizierer {

    public static Map<String, Knotenpunkt[][]> knotenpunktGruppenMapping = new HashMap<>();
//    public static Map<String, String[]> knotenpunktGruppenNamenMapping = new HashMap<>();

    static {
        // Initialisiere die Arrays in der Klasse KnotenpunktGruppen
        Map<String, Knotenpunkt> knotenpunkte = new HashMap<>(); // Beispiel für die Verwendung von Knotenpunkten


        // Definiere die Gruppen und fülle das Mapping
        knotenpunktGruppenMapping.put("A1", new Knotenpunkt[][]{KnotenpunktGruppen.aOneToThree, KnotenpunktGruppen.aSevenToOne});
        knotenpunktGruppenMapping.put("A2", new Knotenpunkt[][]{KnotenpunktGruppen.aOneToThree, KnotenpunktGruppen.up});
        knotenpunktGruppenMapping.put("A3", new Knotenpunkt[][]{KnotenpunktGruppen.aOneToThree, KnotenpunktGruppen.aThreeToFive});
        knotenpunktGruppenMapping.put("A4", new Knotenpunkt[][]{KnotenpunktGruppen.aThreeToFive, KnotenpunktGruppen.right});
        knotenpunktGruppenMapping.put("A5", new Knotenpunkt[][]{KnotenpunktGruppen.aThreeToFive, KnotenpunktGruppen.aFiveToSeven});
        knotenpunktGruppenMapping.put("A6", new Knotenpunkt[][]{KnotenpunktGruppen.aFiveToSeven, KnotenpunktGruppen.down});
        knotenpunktGruppenMapping.put("A7", new Knotenpunkt[][]{KnotenpunktGruppen.aFiveToSeven, KnotenpunktGruppen.aSevenToOne});
        knotenpunktGruppenMapping.put("A8", new Knotenpunkt[][]{KnotenpunktGruppen.aSevenToOne, KnotenpunktGruppen.left});

        knotenpunktGruppenMapping.put("M1", new Knotenpunkt[][]{KnotenpunktGruppen.mOneToThree, KnotenpunktGruppen.mSevenToOne});
        knotenpunktGruppenMapping.put("M2", new Knotenpunkt[][]{KnotenpunktGruppen.mOneToThree, KnotenpunktGruppen.up});
        knotenpunktGruppenMapping.put("M3", new Knotenpunkt[][]{KnotenpunktGruppen.mOneToThree, KnotenpunktGruppen.mThreeToFive});
        knotenpunktGruppenMapping.put("M4", new Knotenpunkt[][]{KnotenpunktGruppen.mThreeToFive, KnotenpunktGruppen.right});
        knotenpunktGruppenMapping.put("M5", new Knotenpunkt[][]{KnotenpunktGruppen.mThreeToFive, KnotenpunktGruppen.mFiveToSeven});
        knotenpunktGruppenMapping.put("M6", new Knotenpunkt[][]{KnotenpunktGruppen.mFiveToSeven, KnotenpunktGruppen.down});
        knotenpunktGruppenMapping.put("M7", new Knotenpunkt[][]{KnotenpunktGruppen.mFiveToSeven, KnotenpunktGruppen.mSevenToOne});
        knotenpunktGruppenMapping.put("M8", new Knotenpunkt[][]{KnotenpunktGruppen.mSevenToOne, KnotenpunktGruppen.left});

        knotenpunktGruppenMapping.put("I1", new Knotenpunkt[][]{KnotenpunktGruppen.iOneToThree, KnotenpunktGruppen.iSevenToOne});
        knotenpunktGruppenMapping.put("I2", new Knotenpunkt[][]{KnotenpunktGruppen.iOneToThree, KnotenpunktGruppen.up});
        knotenpunktGruppenMapping.put("I3", new Knotenpunkt[][]{KnotenpunktGruppen.iOneToThree, KnotenpunktGruppen.iThreeToFive});
        knotenpunktGruppenMapping.put("I4", new Knotenpunkt[][]{KnotenpunktGruppen.iThreeToFive, KnotenpunktGruppen.right});
        knotenpunktGruppenMapping.put("I5", new Knotenpunkt[][]{KnotenpunktGruppen.iThreeToFive, KnotenpunktGruppen.iFiveToSeven});
        knotenpunktGruppenMapping.put("I6", new Knotenpunkt[][]{KnotenpunktGruppen.iFiveToSeven, KnotenpunktGruppen.down});
        knotenpunktGruppenMapping.put("I7", new Knotenpunkt[][]{KnotenpunktGruppen.iFiveToSeven, KnotenpunktGruppen.iSevenToOne});
        knotenpunktGruppenMapping.put("I8", new Knotenpunkt[][]{KnotenpunktGruppen.iSevenToOne, KnotenpunktGruppen.left});

//        knotenpunktGruppenNamenMapping.put("A1", new String[]{"aOneToThree", "aSevenToOne"});
    }

    public static void findKnotenpunktGruppen(String knotenpunktId) {
        // Durchlaufen des knotenpunktGruppenMappings, um die Zeile für den gegebenen Knotenpunkt zu finden
        for (Map.Entry<String, Knotenpunkt[][]> entry : knotenpunktGruppenMapping.entrySet()) {
            String key = entry.getKey();
            Knotenpunkt[][] value = entry.getValue();
            if (key.equals(knotenpunktId)) {
                return;
            }
        }

    }

    public static String getFarben(Knotenpunkt[] knotenpunkte) {
        String[] farben = new String[knotenpunkte.length];
        for (int i = 0; i < knotenpunkte.length; i++) {
            if (knotenpunkte[i] != null) {
                farben[i] = knotenpunkte[i].getSpielerFarbe();
            } else {
                farben[i] = "null";
            }
        }
        return Arrays.toString(farben);
    }


}