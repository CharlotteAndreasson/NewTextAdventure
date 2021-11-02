public class LectureRoom extends Room {
    private String subject;

    public LectureRoom(String name, String description, String subject, boolean isQualified) {
        super(name, description);
        this.subject = subject;
        this.isQualified = false;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        String roomString = getName() + "\n";
        roomString = roomString + getDescription() + getSubject();

        return roomString;
    }
    // This is used to mark that the player is qualified in a subject/has completed the lesson.
    public void setQualified(boolean isQualified) {
        this.isQualified = isQualified;
    }

    // This method is used when entering a classroom. It gives name and description of the classroom
    // and asks if the player wants to answer the subject qualifying question.
    public String enterLectureRoom()  {
        String teacherQualification = "Du är nu i " + getName() + "\n" + getDescription() + getSubject() + "\n" + "\n";
        teacherQualification = teacherQualification + "För att veta om du är behörig i att undervisa i " + getSubject() + "," + "\n";
        teacherQualification = teacherQualification + "måste du först göra ett test. Vill du gå vidare? [JA/NEJ]" + "\n";

        return teacherQualification;
    }
}
