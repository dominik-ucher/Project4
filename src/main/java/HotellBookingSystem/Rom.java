package HotellBookingSystem;

import java.io.FileWriter;
import java.io.IOException;

// Info om rom
public class Rom {
    private int romnr;
    private int antallpersoner;
    
    public Rom(int romnr, int antallpersoner) {
        this.romnr = romnr;
        this.antallpersoner = antallpersoner;
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


    @Override
    public String toString() {
        return romnr +","+antallpersoner;
    }

    public void addToFile(Rom rom) {
        try (FileWriter writer = new FileWriter("rom.txt", true)) {
            writer.write(rom.toString()+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // testing if it works
    public static void main(String[] args) {
        Rom rom1 = new Rom(1, 2);
        Rom rom2 = new Rom(2, 2);
        Rom rom3 = new Rom(3, 2);
        Rom rom4 = new Rom(4, 2);
        rom2.addToFile(rom1);
        rom2.addToFile(rom2);
        rom2.addToFile(rom3);
        rom2.addToFile(rom4);
    }

}
