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

    // This creates a template for printing multiple choice questions.
    public String questionAndOptions() {
        String questionString = getQuestion() + " Ange en bokstav: " + "\n";
        questionString = questionString + "A) " + getOptionA() + "\n" + "B) " + getOptionB() + "\n" + "C) " + getOptionC() + "\n";

        return questionString;
    }
    public String confirmedQualification() {
        return null;
    }
}
