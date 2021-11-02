import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

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
    String yesNo;



    public TextAdventureGame() {
        roomIndex = 0;
        input = new Scanner(in);

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
            out.println("Spelet har sparats");
        } catch (IOException e) {
            out.println("Kunde inte spara spelet");
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
            out.println("Kunde inte ladda sparat spel");
        }
    }
    // Creating room objects of two types
    Room samlingsrummet = new Room("samlingsrummet", "Här samlas eleverna på rasten");
    LectureRoom fransksalen = new LectureRoom("fransksalen", "Här ska du undervisa i ", "franska", false);
    LectureRoom idrottssalen = new LectureRoom("idrottssalen", "Här ska du undervisa i ", "idrott", false);
    LectureRoom matematiksalen = new LectureRoom("matematiksalen", "Här ska du undervisa i ", "matematik", false);

    // Creating questions objects of two types
    Question askDirection = new Question("Vart vill du gå?", "fransksalen", "idrottssalen", "matematiksalen");
    SubjectQuestion frenchQuestion = new SubjectQuestion("franska", "Vilken av följande verbformer är konditionalis?", "veux", "voulais", "voudrais", "C");
    SubjectQuestion sportsQuestion = new SubjectQuestion("idrott", "Från vilken världsdel kommer sporten Lacrosse?", "Oceanien", "Nordamerika", "Europa", "B");
    SubjectQuestion mathsQuestion = new SubjectQuestion("matematik", "Vilket är rätt svar på följande mattetal: 7 + 2 x 5 - 8 = ?", "22", "37", "9", "C");

    // Creating student objects that will be added to an array
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
        // Intro
        out.println("Skriv ditt namn:");
        name = input.nextLine();
        bullshitPoints = 3;
        out.println("Hej " + name + ", välkommen till Bergsjöns högstadieskola. Här ska du vikariera idag." + "\n" +"Du startar med " + bullshitPoints + " bullshitpoäng(bp)." + "\n");
        out.println("Du är nu i " + map[0].toString());

        //Ask user to press enter to continue, big wall of text
        pressEnter();

        running = true;

        // Game loop starts here
        while (running) {


            // Read user input
            out.println(askDirection.questionAndOptions());
            String command = input.nextLine();

            // Check input och act accordingly
            if (command.equalsIgnoreCase("A")) {
                roomIndex = 1;
                if(fransksalen.isQualified){
                   testDone(fransksalen.getSubject()); // If the qualifying subject test has been passed, no need to do it again and the lesson will be finished.
                }else {
                    roomActions((LectureRoom) map[roomIndex], frenchQuestion, "franska"); // Intro to the classroom, qualifying subject questions is asked.
                    if(roomIndex > 0 && running) {
                        lessonActions(fransksalen); // The lesson starts here, students are introduced and different events unfold
                    }
                }
            } else if (command.equalsIgnoreCase("B")) {
                roomIndex = 2;
                if(idrottssalen.isQualified) {
                    testDone(idrottssalen.getSubject());
                } else {
                    roomActions((LectureRoom) map[roomIndex], sportsQuestion, "idrott");
                    if(roomIndex > 0 && running) {
                        lessonActions(idrottssalen);
                    }
                }
            } else if (command.equalsIgnoreCase("C")) {
                roomIndex = 3;
                if(matematiksalen.isQualified) {
                    testDone(matematiksalen.getSubject());
                } else {
                    roomActions((LectureRoom) map[roomIndex], mathsQuestion, "matematik");
                    if(roomIndex > 0 && running) {
                        lessonActions(matematiksalen);
                    }
                }
            } else if (command.equalsIgnoreCase("spara")) {
                save();

            } else if (command.equalsIgnoreCase("ladda")) {
                load();
                out.println(map[roomIndex].getName());

            } else if (command.equalsIgnoreCase("sluta")) {
                running = false;

            } else {
                out.println("Ange ett giltigt kommando");
            }
        }
    }
    // If all qualifying subjects tests have been passed and/or lessons finished, the game will finish
    private void allLessonsFinished() {
        if(fransksalen.isQualified && idrottssalen.isQualified && matematiksalen.isQualified) {
            Scanner paus = new Scanner(in);
            out.println("Tryck \"ENTER\" för att fortsätta..");
            paus.nextLine();
            out.println("Du är tillbaka i " + map[0].getName());
            out.println("Du har tagit dig genom alla dina lektionspass och har klarat spelet." + "\n" + "\n");
            out.println("*************************");
            out.println("*************************");
            out.println("*********GRATTIS*********");
            out.println("*************************");
            out.println("*************************" + "\n");
            running = false;

        }
    }
    // Presentation of player to the students, the lessons starts.
    // Lesson actions which will run smoothly or result in trouble depending on
    // the student characteristics, handling of headmaster and number of bullshitpoints.
    private void lessonActions(LectureRoom room) {
        out.println("Du påbörjar lektionen och presenterar dig för eleverna: \"Jag heter " + name + " och vikarierar idag.\"");
        out.print("Eleverna säger sina namn: ");
        printName(group);
        classRequest = new String[]{" skriver på tavlan", " svarar på en fråga", " läser högt från boken"};
        String studentTasks = getRandom(classRequest); // Picks a task at random from the array studentTasks
        out.println("Du vill att en elev" + studentTasks + ".");
        while(true) {
            out.println("Vilken elev frågar du? Ange ett namn:");
            input = new Scanner(in);
            String studentName = input.nextLine();
            Student foundStudent = null;
            for (Student student : group) {
                if (student.getName().equalsIgnoreCase(studentName)) {
                    foundStudent = student;
                    break;
                }
            }
            if (foundStudent != null) {
                if (foundStudent.getIsTrouble()) { // If the student is a troublemaker the headteacher will arrive (see separate method).
                    out.println(foundStudent.getName() + " börjar ställa till bråk. Hela klassen ballar ur och det blir kaos.");
                    headTeacherComes();
                    if(bullshitPoints > 0) { //Vad ska jag göra här?
                        out.println("Du fortsätter undervisningen. Allt fortlöper utan problem och du avslutar lektionen. Bra jobbat! Gå till nästa klassrum." + "\n");
                        room.setQualified(true);
                        allLessonsFinished();
                        break;
                    } else {
                        running = false;
                        break;
                    }
                } else {
                    out.println(foundStudent.getName() + studentTasks + ". Du fortsätter undervisningen."); // If the student is not a troublemaker,the lesson will run smoothly and finish.
                    out.println("Allt fortlöper utan problem och du avslutar lektionen. Bra jobbat! Gå till nästa klassrum." + "\n");
                    room.setQualified(true);
                    allLessonsFinished();
                    break;
                }
            } else {
                out.println("Ange namn på en elev i klassen:");
            }
        }
    }
    // Randomizing method to pick studentTasks to be performed by the students in the classroom.
    public static String getRandom(String[] classRequests) {
        int rnd = new Random().nextInt(classRequests.length);
        return classRequests[rnd];
    }
    // Presents a subject qualifying question, if passed will make the player qualified in the subject. If failed, it will bring the headteacher to the classroom (see separate method).
    private void roomActions(LectureRoom room, SubjectQuestion question, String subject) {
        out.println(room.enterLectureRoom());
        while(true) {
            yesNo = input.nextLine();
            if (yesNo.equalsIgnoreCase("ja")) {
                out.println(question.questionAndOptions());
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                    room.setQualified(true);
                    out.println("Korrekt! Du är behörig i att undervisa i " + subject + "." + "\n");
                    break;

                } else {
                    out.print("INKORREKT! ");
                    headTeacherComes();
                    break; // Här ska man komma ut ur loopen och sedan vidare till metoden lessonActions

                }
            } else if (yesNo.equalsIgnoreCase("nej")) { // If you choose not to answer the qualifying question, you return to the common room (samlingsrummet).
                roomIndex = 0;
                out.println("Du är tillbaka i " + map[roomIndex].toString());
                break;

            } else {
                out.println("Skriv \"JA\" eller \"NEJ\"");
            }
        }
    }
    // If a lesson has been completed, this message will be printed when reentering the classroom.
    private void testDone(String subject) {
            out.println(subject +"lektionen är slut och eleverna har gått hem för dagen. Gå till ett annat klassrum"+"\n");
    }


    // If the player fails the subject qualifying question, or if there is trouble in the classroom, the headteacher arrives.
    // THe player starts the game with 3 bullshitpoints(bp) and can use them to get out of trouble with the headmaster. If the bp runs out, the game ends.
    private void headTeacherComes() {
        out.println("Rektorn är på väg till klassrummet. Du ligger risigt till..." + "\n");
        if(bullshitPoints > 0) {
            out.println(presentBP() + ". Vill du använda 1 bp? [JA/NEJ]");
            while(true) {
                String yesNo = input.nextLine();
                if (yesNo.equalsIgnoreCase("ja")) {
                    bullshitPoints--;
                    out.println("Du bullshittar dig ur situationen. Bra jobbat!" + "\n" + "Rektorn går tillbaka till sitt kontor." + "\n");
                    out.println(presentBP() + " kvar" + "\n");
                    pressEnter();
                    break;
                } else if (yesNo.equalsIgnoreCase("nej")) {
                    out.println("Du har gjort bort dig. DU FÅR SPARKEN!" + "\n");
                    running = false;
                    break;
                } else {
                    out.println("Skriv \"JA\" eller \"NEJ\"");
                }
            }

        }else{
                System.out.println(presentBP() + " kvar, så du kan inte bullshitta dig ur situationen. DU FÅR SPARKEN!" + "\n");
                running = false;
            }
        }
    // Shows how many bullshitpoints remain for the player.
    private String presentBP() {
        String bp = "Du har " + bullshitPoints + " bp";
        return bp;
    }

    private void pressEnter() {
        System.out.println("Tryck \"ENTER\" för att fortsätta..");
        input.nextLine();
    }

    // Prints the names in an array of students
    private static void printName(Student[] group){
        for(int index = 0; index < group.length; index ++)
            out.print(group[index].getName() + ", ");
        out.println("\n");
    }
    // Printed when the game ends
    public void quit() {
        out.println("Tack för att du vikarierade på Bergsjöns högstadieskola!");
    }

}





