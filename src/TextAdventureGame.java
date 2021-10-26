import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextAdventureGame {
    Scanner input;
    Room[] map;
    Question[] questionList;


    public TextAdventureGame() {
        input = new Scanner(System.in);
    }

    /*
    public static void save(Room room) { //Vad ska finnas i parentesen?
        File file = new File("./save/saved_game.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            String position = String.format("%d, %d"); // Ska jag skriva map[]?
            fileWriter.write(position);
            fileWriter.close();
            System.out.println("The game is saved");
        } catch (IOException e) {
            System.out.println("Could not save the game");
        }
    }

     */

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
    Room samlingsrummet = new Room("samlingsrummet", "Här samlas eleverna på rasten");
    LectureRoom fransksalen = new LectureRoom("fransksalen", "Här ska du undervisa i ", "franska");
    LectureRoom idrottssalen = new LectureRoom("idrottssalen", "Här ska du undervisa i ", "idrott");
    LectureRoom matematiksalen = new LectureRoom("matematiksalen", "Här ska du undervisa i ", "matematik");

    Question askDirection = new Question("Vart vill du gå?", "fransksalen", "idrottssalen", "matematiksalen");
    SubjectQuestion frenchQuestion = new SubjectQuestion("franska", "Vilken av följande verbformer är konditionalis?", "veux", "voulais", "voudrais", "C");
        //fransksalen.setSubjectQuestion(frenchQuestion);
    SubjectQuestion sportsQuestion = new SubjectQuestion("idrott", "Från vilken världsdel kommer sporten Lacrosse?", "Oceanien", "Nordamerika", "Europa", "B");
        //idrottssalen.setSubjectQuestion(sportsQuestion);
    SubjectQuestion mathsQuestion = new SubjectQuestion("matematik", "Vilket är rätt svar på följande mattetal: 7 + 2 x 5 - 8 = ?", "22", "37", "9", "C");
        //matematiksalen.setSubjectQuestion(mathsQuestion);

    public void initialization() {
        map = new Room[]{samlingsrummet, fransksalen, idrottssalen, matematiksalen};
        questionList = new Question[] {askDirection, frenchQuestion, sportsQuestion, mathsQuestion};
    }
    public void runGame() {
        System.out.println("Skriv ditt namn:");
        String name = input.nextLine();
        System.out.println("Hej " + name + ", välkommen till Bergsjöns högstadieskola. Här ska du vikariera idag." + "\n");
        System.out.println("Du är nu i " + map[0].toString());

        //Ask user to press enter to continue, big wall of text
        Scanner paus = new Scanner(System.in);
        System.out.println("Tryck \"ENTER\" för att fortsätta..");
        paus.nextLine();

        boolean running = true;

        // Här börjar spelloopen
        while (running) {

            // Läs in kommando från spelaren
            System.out.println(askDirection.questionAndOptions());
            String command = input.nextLine();

            // Kollar vilket kommando som angivits
            if (command.equalsIgnoreCase("A")) {
                roomActions((LectureRoom) map[1], frenchQuestion, "franska");

            } else if (command.equalsIgnoreCase("B")) {
                roomActions((LectureRoom) map[2], sportsQuestion, "idrott");

            } else if (command.equalsIgnoreCase("C")) {
                roomActions((LectureRoom) map[3], mathsQuestion, "matematik");

                //} else if (command.equalsIgnoreCase("save")) {
                //save(room)

            //} else if (command.equalsIgnoreCase("load")) {
                //LoadSaveGame();

            } else if (command.equalsIgnoreCase("sluta")) {
                running = false;
                //System.out.println("Tack för att du vikarierade på Bergsjöns högstadieskola");
            } else {
                System.out.println("Ange ett giltigt kommando");
            }
        }
    }


    private void roomActions(LectureRoom room, SubjectQuestion question, String subject) {
        System.out.println(room.enterLectureRoom());
        String yesNo = input.nextLine();
        if (yesNo.equalsIgnoreCase("ja")) {
            System.out.println(question.questionAndOptions());
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                System.out.println("Korrekt! Du är behörig i att undervisa i " + subject + "."); // Hur gör jag "franska" till en subjectvariabel?
            } else {
                System.out.println("Inkorrekt! Du får sparken!");
            }
        } else if (yesNo.equalsIgnoreCase("nej")) {
            System.out.println("Du är tillbaka i " + map[0].toString());
        } else {
            System.out.println("Skriv \"JA\" eller \"NEJ\"");
        }
    }

    public void quit() {
        System.out.println("Tack för att du vikarierade på Bergsjöns högstadieskola");
    }
/*
    private void LoadSaveGame() {
        String position = load();
        if (position != null) {
            String[] pos = position.split(", ");
            int oldRow = row;
            int oldCol = col;
            row = Integer.parseInt(pos[0]);
            col = Integer.parseInt(pos[1]);
            if (row >= map.length) {
                System.out.println("Error reading row coordinates from file. Are you cheating?");
                row = oldRow;
                col = oldCol;
            } else {
                if (col >= map[row].length) {
                    System.out.println("Error reading row coordinates from file. Are you cheating?");
                    row = oldRow;
                    col = oldCol;
                }
            }
        }
    }*/
}





