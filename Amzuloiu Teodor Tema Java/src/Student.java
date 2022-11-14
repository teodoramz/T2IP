import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Student implements  Human{
    private String nume;
    private String prenume;
    private PrintWriter hiToProf;
    private String acronimFacultate;
    private int varsta;
    private int anStudiu;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAcronimFacultate() {
        return acronimFacultate;
    }

    public void setAcronimFacultate(String acronimFacultate) {
        this.acronimFacultate = acronimFacultate;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }

    @Override
    public void greeting() {
        hiToProf.println(this.toString());
    }

    @Override
    public int compareTo(Human o) {
        if(this.getVarsta() > o.getVarsta()){
            return 1;
        }
        if(this.getVarsta() < o.getVarsta()){
            return -1;
        }
        return 0;
    }
    public Student(String nume, String prenume, String acronimFacultate, int varsta, int anStudiu) {
        this.nume = nume;
        this.prenume = prenume;
        this.acronimFacultate = acronimFacultate;
        this.varsta = varsta;
        this.anStudiu = anStudiu;
    }

    @Override
    public void doWork() {
        //start class
        //here we will listen on a specific port and welcome students
        String hostname = "127.0.0.1";
        int portNumber = Integer.parseInt("3000");

        try {
            Socket me = new Socket(hostname, portNumber);
            this.hiToProf = new PrintWriter(me.getOutputStream(), true);
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(me.getInputStream()));
            System.out.println("Sunt " + this.getNume() + " " + this.getPrenume() + " si stau la ora...");

            while (!me.isConnected()) {
            }

            this.greeting();
            String message;
            while (me.isConnected()) {
                message = read.readLine();
                if(message!=null){
                    System.out.println(this.getNume() + " " + this.getPrenume() + " aude asta: " +  message);
                }
            }
        } catch (IOException e){
          //  e.printStackTrace();
            System.out.println("Sunt " + this.getNume() + " " + this.getPrenume() + ", s-a terminat ora si am iesit din clasa!");
        }
    }

    @Override
    public String toString() {
        return "[Sd. " + nume + " " + prenume +
                " Anul " + anStudiu +
                " Fac " + acronimFacultate +
                " Varsta " + varsta + " ]";
    }

    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = Integer.parseInt("3000");
        try {
            Socket me = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(me.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(me.getInputStream()));
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String fromServer, fromUser;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
