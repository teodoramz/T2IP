import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class StudentHandler extends Thread {
    private Socket socket;
    private BufferedReader readData;
    private PrintWriter writeData;
    private Profesor professor;


    public StudentHandler(Socket socket, Profesor professor, PrintWriter write, BufferedReader read) {
            this.professor = professor;
            this.socket = socket;
            this.readData = read;
            this.writeData = write;
    }

    @Override
    public void run() {
        this.professor.greeting();
        writeData.println("Sa incepem ora de " + this.professor.getMaterie());
        while(socket.isConnected()){
            try {
                sleep(10000);
                writeData.println("bla bla bla");
                System.out.println("bla bla bla");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //prof is teaching
            //prof transmit important information

        }

    }
}

