package HotellBookingSystem;

public class Admin {
    private String admin;
    private String passord;

    public Admin(String admin, String passord) {
        this.admin = admin;
        this.passord = passord;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
    
}
