package HotellBookingSystemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import HotellBookingSystem.Bestilling;

public class BestillingTest {

    private static final String FILENAME = "bestillinger.txt";

    @Test
    public void testAddToFile() throws IOException {
        // Create a new Bestilling object
        Bestilling bestilling = new Bestilling(1, 2, "2023-03-29", "2023-03-30");

        // Add the Bestilling object to the file
        bestilling.addToFile(bestilling);

        // Check if the Bestilling exists in the file
        boolean found = false;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        while ((line = reader.readLine()) != null) {
            if (line.equals(bestilling.toString())) {
                found = true;
                break;
            }
        }
        reader.close();
        assertTrue(found);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        // Remove the added Bestilling from the file
        String line;
        StringBuffer inputBuffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        while ((line = reader.readLine()) != null) {
            if (!line.equals("1,2,2023-03-29,2023-03-30")) { // Replace with the string representation of the added Bestilling
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
        }
        reader.close();
        FileWriter writer = new FileWriter(FILENAME);
        writer.write(inputBuffer.toString());
        writer.close();
    }
}



