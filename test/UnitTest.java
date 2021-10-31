import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class UnitTest {

    Room biblioteket = new Room("biblioteket", "Här ska du jobba som bibliotekarie");

    @Test
    public void testDescription() {
        String description = biblioteket.getDescription();
        assertEquals("Här ska du jobba som bibliotekarie", description);
    }

    Student Sarah = new Student("Sarah", true);

    @Test
    public void testStudentIsTrouble() {
        Boolean isTrouble = Sarah.getIsTrouble();
        assertTrue(isTrouble);
    }


    @Test
    public void testToString() {
        String toString = biblioteket.toString();
        assertEquals("biblioteket" + "\n" + "Här ska du jobba som bibliotekarie" + "\n", toString);
        }

}
