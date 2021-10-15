import java.util.Scanner;

public class Main {
    // Lägg in save
    // Lägg in load
    public static void main(String[] args) {
        // Initialisering
        Room samlingsrummet = new Room("samlingsrummet", "Här samlas eleverna på rasten");
        Room fransksalen = new LectureRoom("fransksalen", "Här ska du undervisa i ", "franska");
        Room idrottssalen = new LectureRoom("idrottssalen", "Här ska du undervisa i ", "idrott");
        Room matematiksalen = new LectureRoom("matematiksalen", "Här ska du undervisa i ", "matematik");


        Room[][] map = {
                {samlingsrummet, matematiksalen},
                {fransksalen, idrottssalen}
        };
        int row = 0;
        int col = 0;

        Question askDirection = new Question("Vart vill du gå?", "fransksalen", "idrottssalen", "matematiksalen");
        SubjectQuestion frenchQuestion = new SubjectQuestion("franska", "Vilken av följande verbformer är konditionalis?", "veux", "voulais", "voudrais", "C");
        SubjectQuestion sportsQuestion = new SubjectQuestion("idrott", "Från vilken världsdel kommer sporten Lacrosse?", "Oceanien", "Nordamerika", "Europa", "B");
        SubjectQuestion mathsQuestion = new SubjectQuestion("matematik", "Vilket är rätt svar på följande mattetal: 7 + 2 x 5 - 8 = ?", "22", "37", "9", "C");




        Scanner input = new Scanner(System.in);
        System.out.println("Skriv ditt namn:");
        String name = input.nextLine();
        System.out.println("Hej " + name + ", välkommen till Bergsjöns högstadieskola. Här ska du vikariera idag." + "\n");
        System.out.println("Du är nu i " + map[row][col].toString());

        //Ask user to press enter to continue, big wall of text
        Scanner paus = new Scanner(System.in);
        System.out.println("Tryck \"ENTER\" för att fortsätta..");
        paus.nextLine();

        boolean running = true;

        // Här börjar spelloopen
        while(running) {
            // Vilket rum vi är i
            //System.out.println("Du är nu i " + map[row][col].toString());

            // Läs in kommando från spelaren
            System.out.println(askDirection.questionAndOptions());
            String command = input.nextLine();

            // Kollar vilket kommando som angivits
            if (command.equalsIgnoreCase("A")) {
                row = 1;
                col = 0;
                System.out.println(fransksalen.enterLectureRoom());
                //System.out.println("Du är nu i " + map[row][col].toString() + "\n");
                //System.out.println("För att veta om du är behörig i att undervisa i franska, "); // Hur gör jag "franska" till en subjectvariabel?
                //System.out.println("måste du först göra ett test. Vill du gå vidare? [JA/NEJ]");
                String yesNo = input.nextLine();
                if(yesNo.equalsIgnoreCase("ja")) {
                    System.out.println(frenchQuestion.questionAndOptions());
                    String answer = input.nextLine();
                    if(answer.equalsIgnoreCase(frenchQuestion.getCorrectAnswer())) {
                        System.out.println("Korrekt! Du är behörig i att undervisa i franska."); // Hur gör jag "franska" till en subjectvariabel?
                    } else {
                        System.out.println("Inkorrekt! Du får sparken!");
                    }
                }
                else if(yesNo.equalsIgnoreCase("nej")) {
                    System.out.println("Du är tillbaka i " +map[0][0].toString());
                } else {
                    System.out.println("Skriv \"JA\" eller \"NEJ\"");
                }

            } else if (command.equalsIgnoreCase("B")) {
                row = 1;
                col = 1;

            } else if (command.equalsIgnoreCase("C")) {
                row = 0;
                col = 1;

            if(command.equalsIgnoreCase("sluta")) {
                running = false;

            } else {
                    System.out.println("Ange ett giltigt kommando");
                }


            }
        }
        System.out.println("Tack för att du vikarierade på Bergsjöns högstadieskola");


    }
}
