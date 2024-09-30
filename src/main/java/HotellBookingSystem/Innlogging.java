package HotellBookingSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Innlogging for admin for å få tilgang
public class Innlogging {
    private String username;
    private String passord;

    public Innlogging(String username, String passord) {
        if (checkLogin(username, passord)){
            this.username = username;
            this.passord = passord;
        }
        else{
            throw new IllegalAccessError("Wrong username and/or password");
        }
        
    }

    
    public boolean checkLogin(String username, String passord) {
        Path path = Paths.get("adminuser.txt");
        try{
            List<String> lines = Files.readAllLines(path);
            for (String line: lines){
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(passord)){
                    return true;
                }
            }
        }
        catch (IOException e){
            throw new IllegalAccessError("File not found");
        }
            return false;
    }
    
}
