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
}
