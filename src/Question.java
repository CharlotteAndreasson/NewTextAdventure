import java.io.IOException;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;

    public Question(String question, String optionA, String optionB, String optionC) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }
    public String getQuestion() { return question; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }

    public String questionAndOptions() {
        String questionString = getQuestion() + "\n";
        questionString = questionString + getOptionA() + "\n";
        questionString = questionString + getOptionB() + "\n";
        questionString = questionString + getOptionC() + "\n";

        return questionString;
    }


        //Question askDirection = new Question("Vart vill du gå? Ange en bokstav:", "A) fransksalen", "B) biblioteket", "C) matematiksalen");
        //System.out.println("Vart vill du gå? Ange en bokstav:" + "\n" + "A) fransksalen" + "\n" + "B) biblioteket" + "\n" + "C) matematiksalen");





        /*return """
                Vart vill du gå? Ange en bokstav:
                A) fransksalen
                B) biblioteket
                C) matematiksalen""";

         */

}
