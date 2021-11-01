import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class TextAdventureGame {
    Scanner input;
    String name;
    Room[] map;
    Question[] questionList;
    Student[] individual;
    int roomIndex;
    String qualified;
    boolean running;
    int bullshitPoints;
    String[] classRequest;



    public TextAdventureGame() {
        roomIndex = 0;
        input = new Scanner(System.in);

    }
    public void save() {
        File file = new File("./Files/save.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            String position = String.format("%d", roomIndex); //sparas som strängar
            fileWriter.write(position);
            qualified = String.valueOf(map[roomIndex].getIsQualified());
            fileWriter.write(qualified);
            fileWriter.close();
            System.out.println("Spelet har sparats");
        } catch (IOException e) {
            System.out.println("Kunde inte spara spelet");
        }
    }

    public void load() {
        File file = new File("./Files/save.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            String position = fileScanner.nextLine();
            fileScanner.close();
            roomIndex = Integer.parseInt(position);
            //map[roomIndex].isQualified() = Boolean.parseBoolean(qualified); // FIXA
        } catch (FileNotFoundException e) {
            System.out.println("Kunde inte ladda sparat spel");
        }
    }
    Room samlingsrummet = new Room("samlingsrummet", "Här samlas eleverna på rasten");
    LectureRoom fransksalen = new LectureRoom("fransksalen", "Här ska du undervisa i ", "franska", false);
    LectureRoom idrottssalen = new LectureRoom("idrottssalen", "Här ska du undervisa i ", "idrott", false);
    LectureRoom matematiksalen = new LectureRoom("matematiksalen", "Här ska du undervisa i ", "matematik", false);

    Question askDirection = new Question("Vart vill du gå?", "fransksalen", "idrottssalen", "matematiksalen");
    SubjectQuestion frenchQuestion = new SubjectQuestion("franska", "Vilken av följande verbformer är konditionalis?", "veux", "voulais", "voudrais", "C");
    SubjectQuestion sportsQuestion = new SubjectQuestion("idrott", "Från vilken världsdel kommer sporten Lacrosse?", "Oceanien", "Nordamerika", "Europa", "B");
    SubjectQuestion mathsQuestion = new SubjectQuestion("matematik", "Vilket är rätt svar på följande mattetal: 7 + 2 x 5 - 8 = ?", "22", "37", "9", "C");


    Student alice = new Student("Alice", true);
    Student noah = new Student("Noah", false);
    Student elsa = new Student("Elsa", false);
    Student william = new Student("William", false);
    Student selma = new Student("Selma", false);
    Student hugo = new Student("Hugo", true);
    Student wilma = new Student("Wilma", true);
    Student lucas = new Student("Lucas", false);
    Student freja = new Student("Freja", true);
    Student oscar = new Student("Oscar", false);

    Student[] group = new Student[]{alice, noah, elsa, william, selma, hugo, wilma, lucas, freja, oscar};

    public void initialization() {
        map = new Room[]{samlingsrummet, fransksalen, idrottssalen, matematiksalen};
        questionList = new Question[] {askDirection, frenchQuestion, sportsQuestion, mathsQuestion};

    }
    public void runGame() {
        System.out.println("Skriv ditt namn:");
        name = input.nextLine();
        bullshitPoints = 3;
        System.out.println("Hej " + name + ", välkommen till Bergsjöns högstadieskola. Här ska du vikariera idag." + "\n" +"Du startar med " + bullshitPoints + " bullshitpoäng(bp)." + "\n");
        System.out.println("Du är nu i " + map[0].toString());


        //Ask user to press enter to continue, big wall of text
        Scanner paus = new Scanner(System.in);
        System.out.println("Tryck \"ENTER\" för att fortsätta..");
        paus.nextLine();

        running = true;

        // Här börjar spelloopen
        while (running) {

            allLessonsFinished();
            // Läs in kommando från spelaren
            System.out.println(askDirection.questionAndOptions());
            String command = input.nextLine();

            // Kollar vilket kommando som angivits
            if (command.equalsIgnoreCase("A")) {
                roomIndex = 1;
                if(fransksalen.isQualified){
                   testDone(fransksalen.getSubject());
                }else {
                    roomActions((LectureRoom) map[roomIndex], frenchQuestion, "franska");
                    lessonActions();
                }
            } else if (command.equalsIgnoreCase("B")) {
                roomIndex = 2;
                if(idrottssalen.isQualified) {
                    testDone(idrottssalen.getSubject());
                } else {
                    roomActions((LectureRoom) map[roomIndex], sportsQuestion, "idrott");
                    lessonActions();
                }
            } else if (command.equalsIgnoreCase("C")) {
                roomIndex = 3;
                if(matematiksalen.isQualified) {
                    testDone(matematiksalen.getSubject());
                } else {
                    roomActions((LectureRoom) map[roomIndex], mathsQuestion, "matematik");
                    lessonActions();
                }
            } else if (command.equalsIgnoreCase("spara")) {
                save();

            } else if (command.equalsIgnoreCase("ladda")) {
                load();
                System.out.println(map[roomIndex].getName());

            } else if (command.equalsIgnoreCase("sluta")) {
                running = false;

            } else {
                System.out.println("Ange ett giltigt kommando");
            }
        }
    }

    private void allLessonsFinished() {
        if(fransksalen.isQualified && idrottssalen.isQualified && matematiksalen.isQualified) {
            System.out.println("Du är tillbaka i " + map[roomIndex].getName());
            System.out.println("Du har tagit dig genom alla dina lektionspass och har klarat spelet." + "\n");
            System.out.println("*************************");
            System.out.println("*************************");
            System.out.println("*********GRATTIS*********");
            System.out.println("*************************");
            System.out.println("*************************");
            quit();
            running = false;

        }
    }

    private void lessonActions() {
        System.out.println("Du påbörjar lektionen och presenterar dig för eleverna: \"Jag heter " + name + " och jag ska vikariera idag.\"");
        System.out.print("Eleverna säger sina namn: ");
        printName(group);
        classRequest = new String[]{" skriver på tavlan", " svarar på en fråga", " läser högt från boken"};
        String studentTasks = getRandom(classRequest);
        System.out.println("Du vill att en elev" + studentTasks + "." + "\n" + "Vilken elev frågar du? Ange ett namn:");
        input = new Scanner(System.in);
        String studentName = input.nextLine();
        int i;
        int foundAtIndex = -1;
        for (i = 0; i < group.length; i++) {
            if (group[i].getName().equalsIgnoreCase(studentName)) {
                foundAtIndex = i;  // store the actual index for later use
                break;             // no need to search further
            }
        }
        if (foundAtIndex >= 0) {
            if (group[i].getIsTrouble()) {
                System.out.println(group[i].getName() + " börjar ställa till bråk. Hela klassen ballar ur och det blir kaos.");
                headTeacherComes();
                System.out.println("Du fortsätter undervisningen. Allt fortlöper utan problem och du avslutar lektionen. Bra jobbat! Gå till nästa klassrum");
            } else {
                System.out.println(group[i].getName() + studentTasks);
                System.out.println("Du fortsätter undervisningen. Allt fortlöper utan problem och du avslutar lektionen. Bra jobbat! Gå till nästa klassrum." + "\n");
            }
        }else{
            System.out.println("Ange namn på en elev i klassen:"); //Denna är utanför loopen
        }
    }

    public static String getRandom(String[] classRequests) {
        int rnd = new Random().nextInt(classRequests.length);
        return classRequests[rnd];
    }

    private void roomActions(LectureRoom room, SubjectQuestion question, String subject) {
        System.out.println(room.enterLectureRoom());
        String yesNo = input.nextLine();
        if (yesNo.equalsIgnoreCase("ja")) {
            System.out.println(question.questionAndOptions());
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                room.setQualified(true);
                System.out.println("Korrekt! Du är behörig i att undervisa i " + subject + "." + "\n");
            } else {
                System.out.print("INKORREKT! ");
                headTeacherComes();
            }
        } else if (yesNo.equalsIgnoreCase("nej")) {
            roomIndex = 0;
            System.out.println("Du är tillbaka i " + map[roomIndex].toString());
        } else {
            System.out.println("Skriv \"JA\" eller \"NEJ\"");
        }
    }

    private void testDone(String subject) {
            System.out.println(subject +"lektionen är slut och eleverna har gått hem för dagen. Gå till ett annat klassrum"+"\n");
    }

    private void headTeacherComes() {
        System.out.println("Rektorn är på väg till klassrummet. Du ligger risigt till..." + "\n");
        if(bullshitPoints > 0) {
            System.out.println(presentBP() + ". Vill du använda 1 bp? [JA/NEJ]");
            String yesNo = input.nextLine();
            if(yesNo.equalsIgnoreCase("ja")) {
                bullshitPoints--;
                System.out.println("Du bullshittar dig ur situationen. Bra jobbat!" + "\n" + "Rektorn går tillbaka till sitt kontor.");
                System.out.println(presentBP() + " kvar" + "\n");
            }else if(yesNo.equalsIgnoreCase("nej")) {
                System.out.println("Du har gjort bort dig." + "\n" + "DU FÅR SPARKEN!");
                running = false;
            }else {
                System.out.println("Skriv \"JA\" eller \"NEJ\"");
            }

        }else{
                System.out.println(presentBP() + " kvar, så du kan inte bullshitta dig ur situationen." + "\n" + "DU FÅR SPARKEN!" + "\n");
                running = false;
            }
        }

    private String presentBP() {
        String bp = "Du har " + bullshitPoints + " bp";
        return bp;

    }
    // För att använda = //printName(group);
    private static void printName(Student[] group){
        for(int index = 0; index < group.length; index ++)
            System.out.print(group[index].getName() + ", ");
        System.out.println("\n");
    }

    public void quit() {
        System.out.println("Tack för att du vikarierade på Bergsjöns högstadieskola");
    }

}





