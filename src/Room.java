public class Room {
    private String name;
    private String description;
    private SubjectQuestion subjectQuestion;
    protected boolean isQualified;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.isQualified = false;
    }

    public String getName() {return name;}

    public String getSubject() {return "Detta rum har inget ämne";}

    public String getDescription() {return description;}

    public boolean getIsQualified() {
        return isQualified;
    }

    public void setName(String newName) {name = newName; }

    public void setDescription(String newDescription) {description = newDescription;}

    // Create a string representation of a room to show in the main game loop.
    public String toString() {
        String roomString = getName() + "\n";
        roomString = roomString + getDescription() + "\n";
        // lägg till item senare

        return roomString;

    }

    public void setSubjectQuestion(SubjectQuestion subjectQuestion) {
        this.subjectQuestion = subjectQuestion;
    }

    public String enterLectureRoom() {
        return null;
    }

    public SubjectQuestion getSubjectQuestion() {
        return subjectQuestion;
    }
}