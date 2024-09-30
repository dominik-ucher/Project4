package HotellBookingSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import HotellBookingSystem.*;

public class HotellBookingSystemController {
    
    @FXML
    private TabPane tabPane;
    
    //Booking tab
    @FXML
    private Tab bookingTab;
    
    @FXML
    private ChoiceBox<String> AntallValg;
    
    @FXML
    private DatePicker startdato,endedato;
  
    @FXML
    private Button searchrom, bookrom;
    
    @FXML
    private TextField romnr;
    
    @FXML
    private Label rombooked;
    
    @FXML
    private ListView<String> ledigrom;
    
    //Register Admin tab
    @FXML
    private Tab registerAdminTab;
    
    @FXML
    private TextField registeradminusername,registeradminpassword;
    
    @FXML
    private PasswordField admincodepassword;
    
    @FXML
    private Button registerAdminButton;
    
    @FXML
    private Label registerResult;
    
    //Admin tab
    @FXML
    private Tab adminTab;
    
    @FXML
    private TextField adminusername, newregisterrom, newregisterperson;
    
    @FXML
    private PasswordField adminpassword;
    
    @FXML
    private Button adminLoginButton, registernewrom;
    
    @FXML
    private Label InnloggingResult, newromregistered;

    @FXML
    private ListView<Bestilling> bestiltrom;

    
    public void initialize() {
        // Initialize Booking tab
        AntallValg.getItems().addAll("2", "4");
        ledigrom.setVisible(false);
        romnr.setVisible(false);
        bookrom.setVisible(false);
        rombooked.setVisible(false);

        registerResult.setVisible(false);

        InnloggingResult.setVisible(false);
        bestiltrom.setVisible(false);

        newregisterrom.setVisible(false);
        newregisterperson.setVisible(false);
        registernewrom.setVisible(false);
        newromregistered.setVisible(false);
    }
    
    @FXML
    private void handlebookingsearchButtonClick() throws ParseException {
        // Handle the search room button click
        ledigrom.getItems().clear();
        int antallpersoner = Integer.parseInt(AntallValg.getValue().toString());
        List<Rom> roms = ToDo.getRomliste();
        List<Bestilling> bestillinger = ToDo.getBestillingliste();
        LocalDate selectedStartDate = startdato.getValue();
        LocalDate selectedEndDate = endedato.getValue();
        Date startDate = Date.from(selectedStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(selectedEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        try {
            ledigrom.setVisible(true);
            romnr.setVisible(true);
            bookrom.setVisible(true);
            Hotell hotell = new Hotell(roms, bestillinger, antallpersoner, startDate, endDate);
            List<Rom> ledigeRom = hotell.getLedigerom(roms, bestillinger, antallpersoner, startDate, endDate);
            for (Rom hoteller : ledigeRom) {
                int romnr1234 = hoteller.getRomnr();
                int antallpersoner1234 = hoteller.getAntallpersoner();
                String romInfo = "Romnr: " + romnr1234 + ", Antall personer: " + antallpersoner1234;
                ledigrom.getItems().add(romInfo);
            }
        }
        catch (IllegalAccessError e) {
            registerResult.setText("Error: " + e.getMessage());
        }

    }
    
    @FXML
    private void handlebookingClick() {
        // Handle the book room button click
        List<Rom> romBestilt = ToDo.getRomliste();
        LocalDate selectedStartDate = startdato.getValue();
        LocalDate selectedEndDate = endedato.getValue();

        // Convert LocalDate to Date
        Date startDate = Date.from(selectedStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(selectedEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Format Date as String
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = dateFormat.format(startDate);
        String endDateString = dateFormat.format(endDate);
            for (Rom rommers: romBestilt){
                if (rommers.getRomnr() == Integer.parseInt(romnr.getText())){
                    Bestilling bestillinger12345 = new Bestilling(Integer.parseInt(romnr.getText()), Integer.parseInt(AntallValg.getValue().toString()), startDateString, endDateString);
                    bestillinger12345.addToFile(bestillinger12345);
                }
            }
        rombooked.setVisible(true);
        rombooked.setText("Rom " + Integer.parseInt(romnr.getText()) + " is booked");
    }
    
    @FXML
    private void handleadminregisterButtonClick() {
        // Handle the register admin button click
        String username = registeradminusername.getText();
        String password = registeradminpassword.getText();
        int admincode = Integer.parseInt(admincodepassword.getText());
        try {
            AdminRegistrer admin = new AdminRegistrer(username, password, admincode);
            registerResult.setVisible(true);
            registerResult.setText(username + " is now registered");
        } 
        catch (IllegalAccessError e) {
            registerResult.setVisible(true);
            registerResult.setText("Feil admin kode");
        } 
    }

    @FXML
    private void handleadminLogin() {
        // Handle the admin login button click
        String adminusernameField = adminusername.getText();
        String adminpasswordField = adminpassword.getText();
        try {
            List<Bestilling> bestillinger = ToDo.getBestillingliste();
            Innlogging login = new Innlogging(adminusernameField, adminpasswordField);
            InnloggingResult.setVisible(true);
            bestiltrom.setVisible(true);
            newregisterrom.setVisible(true);
            newregisterperson.setVisible(true);
            registernewrom.setVisible(true);
            InnloggingResult.setText("Velykket Innlogging");
            for (Bestilling bestilling : bestillinger) {
                bestiltrom.getItems().add(bestilling);
            }
        }
        catch (IllegalAccessError e) {
            InnloggingResult.setVisible(true);
            InnloggingResult.setText("Error: " + e.getMessage());
        } 
    }

    @FXML
    private void handlenewrom() {
        int romnr = Integer.parseInt(newregisterrom.getText());
        int personer = Integer.parseInt(newregisterperson.getText());
        Rom rom = new Rom(romnr, personer);
        rom.addToFile(rom);
        newromregistered.setVisible(true);
        newromregistered.setText("Rom " + romnr + " is registered");

    }
}
