import java.io.IOException;

public class Functions {

    public static void promptEnterKey(){
        System.out.println("Tryck \"ENTER\" för att fortsätta...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String askDirection() {
        return """
                Vart vill du gå? Ange en bokstav:
                A) fransksalen
                B) biblioteket
                C) matematiksalen""";
    }
}
