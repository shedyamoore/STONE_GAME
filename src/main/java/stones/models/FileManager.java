package stones.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Model class for File management
 * This class follows singleton design pattern
 */
public class FileManager {
    public static final String FILENAME = "gamedata.json";
    private static FileManager instance;
    private List<GameBoard> games;
    private static Logger logger = LogManager.getLogger(FileManager.class);
    Level myLevel = Level.forName("FILEMANAGER", 350);

    /**
     * private constructor to prevent access from outside the class
     */
    private FileManager() {
        games = new ArrayList<>();
        read_logs();
    }

    /**
     * @return instance of this class
     */
    public static FileManager getInstance() {
        if (instance == null)
            instance = new FileManager();
        return instance;
    }

    /**
     * reads the logs and loads them in games List
     */
    private void read_logs() {
        games.clear();
        try {
            String old;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(FILENAME)));
                old = reader.readLine();
                reader.close();
            } catch (FileNotFoundException foe) {
            old = "[]";
            }

            logger.log(myLevel,old);

            JsonArray array = new Gson().fromJson(old, JsonArray.class);


            for (JsonElement element : array) {
                JsonObject obj = (JsonObject) element;
                String player1 = String.valueOf(obj.get("player1Name"));
                String player2 = String.valueOf(obj.get("player2Name"));
                String winner = String.valueOf(obj.get("winner"));
                int moves1 = Integer.parseInt(String.valueOf(obj.get("player1numberOfMoves")));
                int moves2 = Integer.parseInt(String.valueOf(obj.get("player2numberOfMoves")));
                GameBoard gameBoard = new GameBoard(new Player(player1), new Player(player2));
                gameBoard.getPlayer1().setMoves(moves1);
                gameBoard.getPlayer2().setMoves(moves2);
                gameBoard.setWinner(winner);
                games.add(gameBoard);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param game adding a new log to file
     */
    public void addLog(GameBoard game) {
        games.add(game);
        try {
            writeJson(game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to save game session array into json db
     *
     * @param gameboard this is the current game session object
     * @throws java.lang.Exception If for any reason it could not write current game session throw java.lang.Exception
     */
    public void writeJson(GameBoard gameboard) throws Exception {
        String old;
        File file = new File(FILENAME);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            logger.log(myLevel,"Writing to file: "+file.getAbsolutePath());
            old = reader.readLine();
            logger.log(myLevel,old);

            reader.close();
        }catch (FileNotFoundException e){
            old = "[]";
        }



        JsonArray array = new Gson().fromJson(old, JsonArray.class);
        JsonObject sampleObject = new JsonObject();
        sampleObject.addProperty("player1Name", gameboard.getPlayer1().getName());
        sampleObject.addProperty("player2Name", gameboard.getPlayer2().getName());
        sampleObject.addProperty("winner", gameboard.getWinner().getName());
        sampleObject.addProperty("player1numberOfMoves", gameboard.getPlayer1().getMoves());
        sampleObject.addProperty("player2numberOfMoves", gameboard.getPlayer2().getMoves());
        array.add(sampleObject);
        FileWriter writer =
                new FileWriter(
                        new File(FILENAME),false);

        StringBuilder objects = new StringBuilder();
        objects.append("[");
        for (int i = 0; i < array.size(); i++) {
            JsonElement o = array.get(i);
            JsonObject obj = (JsonObject) o;
            objects.append(obj.toString());
            if (i != array.size() - 1)
                objects.append(",");
        }
        objects.append("]");
        writer.write(objects.toString());
        writer.flush();
        writer.close();
    }

    /**
     * @return all games list
     */
    public List<GameBoard> getGames() {
        return games;
    }

    /**
     * @return returns all winners list this is a mehtod to retyurn all the winners in the list by using
     */
    public List<Player> getWinners() {
        read_logs();
        List<Player> players = new ArrayList<>();

        for (GameBoard game : games) {
            if (game.isPlayer1Turn()) {
                players.add(game.getPlayer1());
            } else {
                players.add(game.getPlayer2());
            }
        }
        return players;
    }
}

