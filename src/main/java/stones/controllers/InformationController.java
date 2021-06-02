package stones.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stones.Game;
import stones.models.GameBoard;
import stones.models.GameSession;
import stones.models.Player;

import java.io.IOException;

/**
 * taking input from user, a set of codes that is used to take input from a user
 */
public class InformationController {
    public Label label;
    public TextField secondPlayerField;
    public TextField firstPlayerField;
    /**
     * Application logger
     */
    private static final Logger logger = LogManager.getLogger(InformationController.class);
    /**
     * the continue button is pressed in order to start the game
     * @param actionEvent when continue button is pressed
     *
     */
    public void onStart(ActionEvent actionEvent) {
        String firstPlayer = firstPlayerField.getText();
        String secondPlayer = secondPlayerField.getText();
        if (firstPlayer.length()<3 || secondPlayer.length()<3){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Players names are required and must be longer than 3 digits");
            alert.setContentText(null);
            alert.showAndWait();
            return;
        }


        try {
            logger.info("Player1 name is "+firstPlayer);
            logger.info("Player2 name is "+secondPlayer);
            GameSession.getInstance().setGameBoard(new GameBoard(new Player(firstPlayer),new Player(secondPlayer)));
                Game.setRoot("game_frame");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackBtnClicked(ActionEvent actionEvent) {
        try {
            logger.info("Back Button Clicked");
            Game.setRoot("home");
            //this sets the destination for the command after it is being pressed
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
