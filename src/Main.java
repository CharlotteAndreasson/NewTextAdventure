import java.util.Scanner;

public class Main {
    // Lägg in save
    // Lägg in load
    public static void main(String[] args) {
        // Initialisering
        Room samlingsrummet = new Room("samlingsrummet", "Här samlas eleverna på rasten ");
        Room fransksalen = new Room("fransksalen", "Här ska du undervisa i franska");
        Room biblioteket = new Room("biblioteket", "Här ska du vara bibliotekarie");
        Room matematiksalen = new Room("matematiksalen", "Här ska du undervisa i matematik");


        Room[][] map = {
                {samlingsrummet, matematiksalen},
                {fransksalen, biblioteket}
        };
        int row = 0;
        int col = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Skriv ditt namn:");
        String name = input.nextLine();
        System.out.println("Hej " + name + ", välkommen till Bergsjöns högstadieskola. Här ska du vikariera idag." + "\n");

        boolean running = true;

        // Här börjar spelloopen
        while(running) {
            // Vilket rum vi är i
            System.out.println("Du är nu i " + map[row][col].toString());

            //Ask user to press enter to continue, big wall of text
            Scanner paus = new Scanner(System.in);
            System.out.println("Tryck \"ENTER\" för att fortsätta..");
            paus.nextLine();

            // Läs in kommando från spelaren
            System.out.println("Vart vill du gå? Ange en bokstav:" + "\n" + "A) fransksalen" + "\n" + "B) biblioteket" + "\n" + "C) matematiksalen");
            String command = input.nextLine();

            // Kollar vilket kommando som angivits
            if (command.equalsIgnoreCase("A")) {
                row = 1;
                col = 0;

            } else if (command.equalsIgnoreCase("B")) {
                row = 1;
                col = 1;

            } else if (command.equalsIgnoreCase("C")) {
                row = 0;
                col = 1;

            } else {
                    System.out.println("Du måste ange A, B eller C");
                }

            if(command.equalsIgnoreCase("sluta")) {
                running = false;
            }
        }
        System.out.println("Tack för att du vikarierade på Bergsjöns högstadieskola");


    }
}
