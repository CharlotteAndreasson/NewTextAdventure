import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextAdventureGame {
    int row;
    int col;
    Scanner input;

    Room[][] map;


    public static void save(int row, int col) {
        File file = new File("./save/saved_game.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            String position = String.format("%d, %d", row, col);
            fileWriter.write(position);
            fileWriter.close();
            System.out.println("The game is saved");
        } catch (IOException e) {
            System.out.println("Could not save the game");
        }
    }

    public static String load() {
        File file = new File("./save/saved_game.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            String position = fileScanner.nextLine();
            fileScanner.close();
            return position;
        } catch (FileNotFoundException e) {
            System.out.println("Could not load a saved game");
        }
        return null;
    }

    private void updatePLayerPosition(String command) {
        
    }

}
