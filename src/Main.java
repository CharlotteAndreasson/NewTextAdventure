import java.util.Scanner;

public class Main {
    // Lägg in save
    // Lägg in load
    public static void main(String[] args) {
        // Initialisering
        Room yard = new Room("The yard", "A gravel yard with random old debris");
        Room pigsty = new Room("The pigsty", "A noisy and smelly room full of cute pigs");
        Room cricketShed = new Room("The cricket shed", "A cramped shed with crickets EVERYWHERE");
        Room llamaPen = new Room("The llama pen", "A pen with tall juicy grass och floofy llamas");


        Room[][] map = {
                {yard, llamaPen},
                {pigsty, cricketShed}
        };
        int row = 0;
        int col = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Charlie's Farm");

        boolean running = true;

        // Här börjar spelloopen
        while(running) {
            // Vilket rum vi är i
            System.out.println(map[row][col].toString());

            // Läs in kommando från spelaren
            System.out.print("> ");
            String command = input.nextLine();

            // 3. Dela upp kommandot i olika delar, varje ord blir en sträng i en array
            // Vi delar upp det inmatade värdet vid varje mellanslag
            String [] commandParts = command.split(" ");

            // 4. Kollar vilket "huvudkommando" som angivits
            //    Dessa är:
            //      - go
            //      - save
            //      - load
            //      - quit
            if(commandParts[0].equalsIgnoreCase("go")) {

                if(commandParts.length >= 2) {
                    if (commandParts[1].equalsIgnoreCase("north")) {
                        row--;
                        if(row < 0) {
                            row = 0;
                        }
                    }
                    else if(commandParts[1].equalsIgnoreCase("south")) {
                        row++;
                        if(row >= map.length) {
                            row--;
                        }
                    }
                    else if(commandParts[1].equalsIgnoreCase("east")) {
                        col++;
                        if(col >= map[row].length) { //varför map[row].length, inte col?
                            col--;
                        }
                    }
                    else if(commandParts[1].equalsIgnoreCase("west")) {
                        col--;
                        if(col < 0) {
                            col = 0;
                        }
                    }
                    System.out.println("Going " + commandParts[1]);
                }
                else {
                    System.out.println("You cannot go without any direction");
                }
            }
            if(command.equalsIgnoreCase("quit")) {
                running = false;
            }
        }
        System.out.println("Thanks for playing Charlie's Farm");


    }
}
