package HotellBookingSystemTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import HotellBookingSystem.Innlogging;

public class InnloggingTest {
    
    @Test
    public void validInnlogging() {
        String username = "admin";
        String password = "admin";

        Innlogging innlogging = new Innlogging(username, password);

        assertNotNull(innlogging);
    }

    @Test
    public void invalidInnlogging() {
        String username = "False";
        String password = "False";

        IllegalAccessError exception = assertThrows(IllegalAccessError.class, () -> {Innlogging innlogging = new Innlogging(username, password);});
        String expectedMessage = "Wrong username and/or password";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }
}
