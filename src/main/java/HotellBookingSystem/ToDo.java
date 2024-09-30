package HotellBookingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import HotellBookingSystem.*;

public class ToDo {

    public static List<Rom> getRomliste() {
        List<Rom> romListe = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File("rom.txt"))) {
            while (scanner.hasNextLine()) {
                String roms = scanner.nextLine().trim();
                String[] parts = roms.split(",");
                Rom rom = new Rom(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
                romListe.add(rom);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return romListe;
    }

    public static List<Bestilling> getBestillingliste() {
        List<Bestilling> bestillingListe = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File("bestillinger.txt"))) {
            while (scanner.hasNextLine()) {
                String bestil = scanner.nextLine().trim();
                String[] parts = bestil.split(",");
                Bestilling bestilling = new Bestilling(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),parts[2], parts[3]);
                bestillingListe.add(bestilling);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return bestillingListe;
    }

    public static void main(String[] args) {
        List<Bestilling> bestillingsListe = getBestillingliste();
        System.out.println(bestillingsListe);
    }
}
