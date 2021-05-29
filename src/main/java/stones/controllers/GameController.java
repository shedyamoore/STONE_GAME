package stones.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stones.Game;
import stones.models.FileManager;
import stones.models.GameBoard;
import stones.models.GameSession;
import stones.models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contoller class for main gameview
 * creation of buttons and platform for the input of the player names
 */
public class GameController implements Initializable {
    private static final Logger logger = LogManager.getLogger();
    public Label player1Name;
    public Label player2Name;
    public Button btnA;
    public Button btnB;
    public Button btnC;
    public Button btnD;
    public Button btnE;
    public Button btnF;
    public Button btnG;
    public Button btnH;
    public Button btnI;
    public Button btnJ;
    public Button btnK;
    public Button btnL;
    public Button btnM;
    public Button btnN;
    public Button btnO;
    public Button btnP;
    private GameBoard gameBoard;

    /**
     * default constructor to get gameboard instance from GameSession
     */
    public GameController() {
        gameBoard = GameSession.getInstance().getGameBoard();
    }

    /**
     * method to initialize interface to load the graphics
     * @param url
     * @param resourceBundle
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (gameBoard != null) {
            player1Name.setText(gameBoard.getPlayer1().getName());
            player2Name.setText(gameBoard.getPlayer2().getName());
            logger.info("Player 1 Name is "+player1Name);
            logger.info("Player 2 Name is "+player2Name);
        }
        updateTurn();
    }

    /**
     * updates the turn on screen
     * the button update  is used to keep track of the turn and moves
     */
    private void updateTurn() {
        if (gameBoard.isPlayer1Turn()) {
            player1Name.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            player2Name.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            player2Name.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            player1Name.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        logger.info("Update turn method");
    }


    /**
     * when the skip button is pressed it skips a turn and the next player moves
     * game moves automatically but when skip is pressed it become manual
     * @param actionEvent
     */
    public void onSkipBtnClicked(ActionEvent actionEvent) {
        gameBoard.skipTurn();
        updateTurn();
    }

    /**
     * when the reset button is pressed it resets the game board and updates the views
     * @param actionEvent
     *
     */
    public void onResetBtnClicked(ActionEvent actionEvent) {
        gameBoard = new GameBoard(new Player(gameBoard.getPlayer1().getName()),new Player(gameBoard.getPlayer2().getName()));
        updateTurn();
        updateBoard();
    }

    /**
     * when back button is pressed application goes back to home screen
     * @param actionEvent
     *
     */
    public void onBackBtnClicked(ActionEvent actionEvent) {
        try {
            Game.setRoot("home");
            logger.info("Back button click");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * makes a move on the screen
     * @param actionEvent
     *
     */
    public void makeMove(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        logger.info("button Id is "+button.getId());
        switch (button.getId()) {
            case "btnA":
                gameBoard.makeTurn(0, 0);
                break;
            case "btnB":
                gameBoard.makeTurn(0, 1);
                break;
            case "btnC":
                gameBoard.makeTurn(0, 2);
                break;
            case "btnD":
                gameBoard.makeTurn(0, 3);
                break;
            case "btnE":
                gameBoard.makeTurn(1, 0);
                break;
            case "btnF":
                gameBoard.makeTurn(1, 1);
                break;
            case "btnG":
                gameBoard.makeTurn(1, 2);
                break;
            case "btnH":
                gameBoard.makeTurn(1, 3);
                break;
            case "btnI":
                gameBoard.makeTurn(2, 0);
                break;
            case "btnJ":
                gameBoard.makeTurn(2, 1);
                break;
            case "btnK":
                gameBoard.makeTurn(2, 2);
                break;
            case "btnL":
                gameBoard.makeTurn(2, 3);
                break;
            case "btnM":
                gameBoard.makeTurn(3, 0);
                break;
            case "btnN":
                gameBoard.makeTurn(3, 1);
                break;
            case "btnO":
                gameBoard.makeTurn(3, 2);
                break;
            case "btnP":
                gameBoard.makeTurn(3, 3);
                break;
        }
        //updating the board
        updateBoard();
        if (gameBoard.getWinner()!=null){
            FileManager.getInstance().addLog(gameBoard);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setHeaderText("Player: "+gameBoard.getWinner().getName()+" has won");
            logger.info("Player: "+gameBoard.getWinner().getName()+" has won");
            alert.setContentText(null);
            alert.showAndWait();
            gameBoard.reset();
        }
        updateBoard();
        updateTurn();
    }

    /**
     * updates the grid turns on and of the views on the screen
     * by looping through the array  and setting the visibility of the buttons
     */
    private void updateBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = getButton(i * 4 + j);

                if (!gameBoard.getArray()[i][j]) {
                    button.setVisible(false);
                } else {
                    button.setVisible(true);
                }
            }

        }
    }
    /**
     * method to get the button based on its position
     * @param index
     *
     */
    private Button getButton(int index) {
        logger.info("Button index is "+ index);
        switch (index) {
            case 0:
                return btnA;
            case 1:
                return btnB;
            case 2:
                return btnC;
            case 3:
                return btnD;
            case 4:
                return btnE;

            case 5:
                return btnF;
            case 6:
                return btnG;
            case 7:
                return btnH;
            case 8:
                return btnI;
            case 9:
                return btnJ;
            case 10:
                return btnK;
            case 11:
                return btnL;
            case 12:
                return btnM;
            case 13:
                return btnN;
            case 14:
                return btnO;
            case 15:
                return btnP;
        }
        return null;
    }
}
