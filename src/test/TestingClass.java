package test;

import javafx.application.Application;
import org.junit.BeforeClass;
import org.junit.Test;
import sample.Main;

import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;

public class TestingClass {

    private volatile boolean success = false;

    /**
     * Test that a JavaFX application launches.
     */
    @Test
    public void testMain() {
        Thread thread = new Thread() { // Wrapper thread.
            @Override
            public void run() {
                try {
                    Application.launch(Main.class); // Run JavaFX application.
                    success = true;
                } catch (Throwable t) {
                    if (t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        // We expect to get this exception since we interrupted
                        // the JavaFX application.
                        success = true;
                        return;
                    }
                    // This is not the exception we are looking for so log it.
                    Logger.getLogger(TestingClass.class.getName()).log(Level.SEVERE, null, t);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch (InterruptedException ex) {
            // We don't care if we wake up early.
        }
        thread.interrupt();
        try {
            thread.join(1); // Wait 1 second for our wrapper thread to finish.
        } catch (InterruptedException ex) {
            // We don't care if we wake up early.
        }
        assertTrue(success);
    }


    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        // Initialise Java FX

        System.out.printf("About to launch FX App\n");
        Thread t = new Thread("JavaFX Init Thread") {
            public void run() {
                Application.launch(Main.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.printf("FX App thread started\n");
        Thread.sleep(500);
    }
}