package stones.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stones.Game;
import stones.models.FileManager;
import stones.models.Player;

import java.io.IOException;

/**
 * home page for the application
 */
public class HomeController {
    public Label label;
    private static final Logger logger = LogManager.getLogger(HomeController.class);
    /**
     * when the start button is pressed we call a method that trigger it which shows a dialog after
     * @param actionEvent method that triggers when the start button is pressed
     */
    public void onStart(ActionEvent actionEvent) {
        try {
            //            TextInputDialog td = new TextInputDialog("enter any text");
//            td.showAndWait();
//            label.setText(td.getEditor().getText());
                Game.setRoot("information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The score button is pressed  when the high score button is clicked
     * @param actionEvent Method that get triggers  when the score button is pressed
     */
    public void onHighScoreBtnClicked(ActionEvent actionEvent) {

        // this codes controls the dialogue box
        Dialog dlg = new Dialog();
        dlg.setTitle("Top 5 Players");
        logger.info("On High Score click");
        ObservableList<Player> gameSessions = FXCollections.observableList(FileManager.getInstance().getWinners());
        TableView<Player> listTable = new TableView<>();
        TableColumn columnName = new TableColumn("Player name");
        columnName.setCellValueFactory(
                new PropertyValueFactory<Player,String>("name"));
        columnName.setMinWidth(60);

        TableColumn columnScore = new TableColumn("moves");
        columnScore.setCellValueFactory(
                new PropertyValueFactory<Player,String>("moves"));
        columnScore.setMinWidth(60);
        columnScore.setSortType(TableColumn.SortType.ASCENDING);
        // add columns to the table
        listTable.setItems(gameSessions);
        listTable.getColumns().addAll(columnName, columnScore);
        listTable.setPrefSize(250,200);
        listTable.refresh();
        listTable.getSortOrder().add(columnScore);
        listTable.sort();
        //add listable as the content of the table
        dlg.getDialogPane().setContent(listTable);
        //create a button to close the dialog(close)
        ButtonType buttonTypeOk = new ButtonType("close", ButtonBar.ButtonData.OK_DONE);
        //add the button to the dialog
        dlg.getDialogPane().getButtonTypes().add(buttonTypeOk);
        //show the dialog
        dlg.showAndWait();
    }
}
