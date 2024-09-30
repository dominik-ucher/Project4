package HotellBookingSystemTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import HotellBookingSystem.AdminRegistrer;

public class RegisterAdminTest {
    
    @Test
    public void validRegister() {
        String username = "valid";
        String password = "valid";
        int adminkode = 1234;

        AdminRegistrer adminregister = new AdminRegistrer(username, password, adminkode);

        assertNotNull(adminregister);
    }

    @Test
    public void invalidRegister() {
        String username = "invalid";
        String password = "invalid";
        int adminkode = 1111;

        IllegalAccessError exception = assertThrows(IllegalAccessError.class, () -> {AdminRegistrer adminregister = new AdminRegistrer(username, password, adminkode);});
        String expectedMessage = "Feil admin kode";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
