public class Student {
    private String name;
    private boolean isTrouble;

    // Object "Student" has variables name, and if the student is a troublemaker or not.
    public Student(String name, boolean isTrouble) {
        this.name = name;
        this.isTrouble = isTrouble;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsTrouble() {
        return isTrouble;
    }

    public void setTrouble(boolean trouble) {
        isTrouble = trouble;

    }

}

