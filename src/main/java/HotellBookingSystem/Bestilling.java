package HotellBookingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Bestilling {
    
    private int romnr;
    private int antallpersoner;
    private String startdato;
    private String endedato;
    
    public Bestilling(int romnr, int antallpersoner, String startdato, String endedato) {
        this.romnr = romnr;
        this.antallpersoner = antallpersoner;
        this.startdato = startdato;
        this.endedato = endedato;
    }

    public int getRomnr() {
        return romnr;
    }

    public void setRomnr(int romnr) {
        this.romnr = romnr;
    }

    public int getAntallpersoner() {
        return antallpersoner;
    }

    public void setAntallpersoner(int antallpersoner) {
        this.antallpersoner = antallpersoner;
    }

    public String getStartdato() {
        return startdato;
    }

    public void setStartdato(String startdato) {
        this.startdato = startdato;
    }

    public String getEndedato() {
        return endedato;
    }

    public void setEndedato(String endedato) {
        this.endedato = endedato;
    }

    public boolean overlapsWith(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && end1.after(start2);
    }
    

    @Override
    public String toString() {
        return romnr +","+antallpersoner+","+startdato+","+ endedato;
    }


    public void addToFile(Bestilling bestilling) {
        try (FileWriter writer = new FileWriter("bestillinger.txt", true)) {
            writer.write(bestilling.toString()+"\n");
            writer.flush();
            System.out.println("Registered Succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Bestilling bestilling1 = new Bestilling(1, 2, "2023-03-29", "2023-03-30");
        bestilling1.addToFile(bestilling1);
        
    }
}
