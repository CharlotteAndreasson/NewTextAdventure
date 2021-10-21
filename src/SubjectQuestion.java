public class SubjectQuestion extends Question {
    private String correctAnswer;
    private String subject;
    private String confirmedQualification;

    public SubjectQuestion(String subject, String question, String optionA, String optionB, String optionC, String correctAnswer) {
        super(question, optionA, optionB, optionC);
        this.subject = subject;
        this.correctAnswer = correctAnswer;

    }
    public String getSubject() {
        return subject;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String confirmedQualification() {
        String qualification = "Korrekt! Du är behörig i att undervisa i " + getSubject();
        return qualification;
    }



    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
