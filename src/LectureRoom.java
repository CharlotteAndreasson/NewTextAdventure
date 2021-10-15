public class LectureRoom extends Room {
    private String subject;

    public LectureRoom(String name, String description, String subject) {
        super(name, description);
        this.subject = subject;
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
    public String enterLectureRoom()  {
        String teacherQualification = "Du är nu i " + getName() + "\n" + getDescription() + getSubject() + "\n" + "\n";
        teacherQualification = teacherQualification + "För att veta om du är behörig i att undervisa i " + getSubject() + "," + "\n";
        teacherQualification = teacherQualification + "måste du först göra ett test. Vill du gå vidare? [JA/NEJ]";

        return teacherQualification;
    }
}
