package stones.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
     * @param actionEvent when continue button is pressed
     *                    it checks if the user has entered valid name or not
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
            /*TextInputDialog td = new TextInputDialog("enter any text");
            td.showAndWait();
            label.setText(td.getEditor().getText());*/
            GameSession.getInstance().setGameBoard(new GameBoard(new Player(firstPlayer),new Player(secondPlayer)));
                Game.setRoot("game_frame");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackBtnClicked(ActionEvent actionEvent) {
        try {
            Game.setRoot("home");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
