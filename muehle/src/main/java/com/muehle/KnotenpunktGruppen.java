package com.muehle;

import java.util.Map;

public class KnotenpunktGruppen {
    public static Knotenpunkt[] aOneToThree;
    public static Knotenpunkt[] aThreeToFive;
    public static Knotenpunkt[] aFiveToSeven;
    public static Knotenpunkt[] aSevenToOne;
    public static Knotenpunkt[] mOneToThree;
    public static Knotenpunkt[] mThreeToFive;
    public static Knotenpunkt[] mFiveToSeven;
    public static Knotenpunkt[] mSevenToOne;
    public static Knotenpunkt[] iOneToThree;
    public static Knotenpunkt[] iThreeToFive;
    public static Knotenpunkt[] iFiveToSeven;
    public static Knotenpunkt[] iSevenToOne;
    public static Knotenpunkt[] left;
    public static Knotenpunkt[] up;
    public static Knotenpunkt[] right;
    public static Knotenpunkt[] down;

    public static void initializeArrays(Map<String, Knotenpunkt> knotenpunkte) {
        // Die jeweils in 3-er Gruppe zusammengefassten Felder ergeben Mühle

        aOneToThree = new Knotenpunkt[]{knotenpunkte.get("A1"), knotenpunkte.get("A2"), knotenpunkte.get("A3")};
        aThreeToFive = new Knotenpunkt[]{knotenpunkte.get("A3"), knotenpunkte.get("A4"), knotenpunkte.get("A5")};
        aFiveToSeven = new Knotenpunkt[]{knotenpunkte.get("A5"), knotenpunkte.get("A6"), knotenpunkte.get("A7")};
        aSevenToOne = new Knotenpunkt[]{knotenpunkte.get("A7"), knotenpunkte.get("A8"), knotenpunkte.get("A1")};

        mOneToThree = new Knotenpunkt[]{knotenpunkte.get("M1"), knotenpunkte.get("M2"), knotenpunkte.get("M3")};
        mThreeToFive = new Knotenpunkt[]{knotenpunkte.get("M3"), knotenpunkte.get("M4"), knotenpunkte.get("M5")};
        mFiveToSeven = new Knotenpunkt[]{knotenpunkte.get("M5"), knotenpunkte.get("M6"), knotenpunkte.get("M7")};
        mSevenToOne = new Knotenpunkt[]{knotenpunkte.get("M7"), knotenpunkte.get("M8"), knotenpunkte.get("M1")};

        iOneToThree = new Knotenpunkt[]{knotenpunkte.get("I1"), knotenpunkte.get("I2"), knotenpunkte.get("I3")};
        iThreeToFive = new Knotenpunkt[]{knotenpunkte.get("I3"), knotenpunkte.get("I4"), knotenpunkte.get("I5")};
        iFiveToSeven = new Knotenpunkt[]{knotenpunkte.get("I5"), knotenpunkte.get("I6"), knotenpunkte.get("I7")};
        iSevenToOne = new Knotenpunkt[]{knotenpunkte.get("I7"), knotenpunkte.get("I8"), knotenpunkte.get("I1")};

        left = new Knotenpunkt[]{knotenpunkte.get("A8"), knotenpunkte.get("M8"), knotenpunkte.get("I8")};
        up = new Knotenpunkt[]{knotenpunkte.get("A2"), knotenpunkte.get("M2"), knotenpunkte.get("I2")};
        right = new Knotenpunkt[]{knotenpunkte.get("A4"), knotenpunkte.get("M4"), knotenpunkte.get("I4")};
        down = new Knotenpunkt[]{knotenpunkte.get("A6"), knotenpunkte.get("M6"), knotenpunkte.get("I6")};
    }
}