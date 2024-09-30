package HotellBookingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import HotellBookingSystem.*;

// Legger til en admin bruker i en fil
public class AdminRegistrer {
    private String admin;
    private String passord;
    private final int adminkode = 1234;

    public AdminRegistrer(String admin, String passord, int adminkode) {
        if (checkUsername(admin)){
            throw new IllegalAccessError("Username already exists");
        }
        this.admin = admin;
        this.passord = passord;
        if (!(this.adminkode == adminkode)){
            throw new IllegalAccessError("Feil admin kode");
        }
        addToAdminFile(admin, passord);
    }

    public boolean checkUsername(String admin) {
        Path path = Paths.get("adminuser.txt");
        try{
            List<String> lines = Files.readAllLines(path);
            for (String line: lines) {
                String[] parts = line.split(",");
                if (parts[0].equals(admin)){
                    return true;
                }
            }
        }
        catch (IOException e){
            throw new IllegalAccessError("File not found");
        }
        return false;
    }

    public void addToAdminFile(String username, String password) {
        try (FileWriter writer = new FileWriter("adminuser.txt", true)) {
            writer.write(username + "," + password + "\n");
            writer.flush();
            System.out.println(username+password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
