package com.muehle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    public static Map<String, Knotenpunkt> knotenpunkte = new HashMap<>();
    public static StartScreenController controller;
    public static Stage gameStage;
    private static SpielController spielController;

    public static void startGame(String playerName1, String playerName2) throws IOException {
        gameStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("muehleSpiel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gameStage.setTitle("Herzlich Willkommen zum Mühle-Spiel");
        gameStage.setScene(scene);
        gameStage.show();

        // Erstelle die Knotenpunkte und fülle die Map
        knotenpunkte = KnotenpunkteErsteller.erstelleKnotenpunkte(fxmlLoader);

        // Erstelle die Knotenpunktgruppen und fülle die Map
        KnotenpunktGruppen.initializeArrays(knotenpunkte);

        // Holen Sie den SpielController aus dem FXMLLoader
        spielController = fxmlLoader.getController();
        SpielController.spieler1 = new Spieler("white");
        SpielController.spieler1.setSpielerName(playerName1);
        SpielController.spieler2 = new Spieler("black");
        SpielController.spieler2.setSpielerName(playerName2);
        spielController.setMuehleLabelText(playerName1);
        spielController.spielBeginn();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Startbildschirm");
        stage.setScene(scene);
        stage.show();
        // Rufe die Methode auf dem Controller des Startbildschirms auf, um das Gewinner-Label anzuzeigen
        controller = fxmlLoader.getController();
    }
}
