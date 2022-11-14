import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

public class Profesor implements Human{

    private  ServerSocket server;
    private Calendar classStartedTime;
    private String nume;
    private String prenume;
    private String acronimFacultate;
    private int varsta;
    private String materie;
    private PrintWriter hiToAll;
    private ArrayList<Human> Class;

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
    public Calendar getDate(){return classStartedTime;}

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public Profesor(String nume, String prenume, String acronimFacultate, int varsta, String materie) {
        this.nume = nume;
        this.prenume = prenume;
        this.acronimFacultate = acronimFacultate;
        this.varsta = varsta;
        this.materie = materie;
        this.Class = new ArrayList<>();
    }

    @Override
    public void greeting() {
        this.hiToAll.println(this.toString());
    }
    private int ceva = 0;
    public void classIsOver() throws IOException {
        this.server.close();
    }
    @Override
    public void doWork() {
        this.classStartedTime = Calendar.getInstance();
        int port = 3000;
        System.out.println("Sa incepem ora!");

        Human prof = this;
        Class.add(prof);

        try {
             this.server = new ServerSocket(port);
             this.classStartedTime = Calendar.getInstance();
            this.classStartedTime.add(Calendar.SECOND,60);
            System.out.println("Sunt "+ this.getNume() + " " + this.getPrenume()+ " si am inceput sa predau!");
            Clock1 clock1 = new Clock1(this);

            while(!server.isClosed()) {
                Socket client = server.accept();
                BufferedReader readData = new BufferedReader( new InputStreamReader(client.getInputStream()));
                PrintWriter writeData = new PrintWriter( client.getOutputStream(),true);
                this.hiToAll = writeData;
                StudentHandler student = new StudentHandler(client, this, writeData, readData);

                String data = null;
                while(data == null){

                    //adding student to list
                    data = readData.readLine();
                    String[] dataSplit = data.split(" ");
                    if(dataSplit[0].compareTo("[Sd.")!=0){
                        data = null;
                        System.out.println(dataSplit[0]);
                    }else{

                    Human person = new Student(dataSplit[1], dataSplit[2],
                            dataSplit[6], Integer.parseInt(dataSplit[8]), Integer.parseInt(dataSplit[4]));
                    Class.add(person);


                    break;}
                }


                Thread thread = new Thread(student);
                thread.start();
                Thread thread1 = new Thread(clock1);
                thread1.start();

            }

            //when class is over, show who were at class


        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("In clasa au fost urmatorii:");
            Collections.sort(Class);

            Iterator<Human> it = Class.iterator();
            while (it.hasNext()){
                System.out.println(it.next().toString());
            }

            System.out.println("Ora s-a terminat!");
           System.exit(0);


        }


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

    @Override
    public String toString() {
        return "[Prof." + nume + " " + prenume +
                ", Fac." + acronimFacultate +
                ", Varsta " + varsta +
                ", Materie predata - " + materie +"]";
    }
}
