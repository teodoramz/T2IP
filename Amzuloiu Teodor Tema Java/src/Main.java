import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //va rula 1 minut si dupa va inchide conexiunea
        // cei 2 studenti de test sunt in instancesd1 si 2
        Profesor prof = new Profesor("Mihai", "Petre", "ATM", 28, "Bio-Fizica");
        prof.doWork();
    }
}