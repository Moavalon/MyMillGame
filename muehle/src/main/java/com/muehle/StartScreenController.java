package com.muehle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartScreenController {

    @FXML
    private Button startButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Button exitButton;
    @FXML
    private Label textLabel;
    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private Label winner;





    @FXML
    public void startGame() throws IOException {
        String playerName1 = player1.getText();
        String playerName2 = player2.getText();
        if (playerName1.isEmpty()) {
            playerName1 = "Spieler1";
        }
        if (playerName2.isEmpty()) {
            playerName2 = "Spieler2";
        }
        Main.startGame(playerName1, playerName2);

    }

    @FXML
    public void showOptions() {
        // Code, um Optionen anzuzeigen
        System.out.println("Optionen angezeigt.");
    }

    @FXML
    public void exitGame() {
        // Code, um das Spiel zu beenden
        System.out.println("Spiel beendet.");
        System.exit(0);
    }

    public void setGewinnerLabel(String gewinner) {
        winner.setText(gewinner + " hat gewonnen !");
    }
}
