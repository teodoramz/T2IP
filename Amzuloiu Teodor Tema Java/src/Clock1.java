import java.io.IOException;
import java.util.Calendar;

public class Clock1 extends Thread{
    private  Profesor prof;
    public Clock1(Profesor prof) {
        this.prof = prof;
    }

    @Override
    public void run() {
        while(true){
            if(prof.getDate().equals(Calendar.getInstance()) == true){
                break;
            }
        }
        try {
            prof.classIsOver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
