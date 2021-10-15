public class SubjectQuestion extends Question {
    private String correctAnswer;
    private String subject;

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

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
