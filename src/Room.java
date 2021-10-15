public class Room {

    // We have variables for name, description and other parameters since this is necessary to determine the players
    // current state in the world.

    private String name;
    private String description;


    // konstruktor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;

    }
    public String getName() {return name;}

    public String getDescription() {return description;}

    public void setName(String newName) {name = newName; }
    public void setDescription(String newDescription) {description = newDescription;}

    // Create a string representation of a room to show in the main game loop.
    public String toString() {
        String roomString = getName() + "\n";
        roomString = roomString + getDescription() + "\n";
        // lägg till item senare

        return roomString;

    }

    public String enterLectureRoom() {
        return null;
    }
}