package co.com.ies.pruebas.simulacion;

import javax.swing.*;
import java.util.List;

/**
 * Created by Isai B. Cicourel
 */
public class Loader {


    /**
     * Creates an Example SwingWorker
     */
    public SwingWorker createWorker() {
        return new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Start Progress
                setProgress(0);
                waitFor(500);

                // Example Loop
                for (int iCount = 1; iCount <= 20; iCount++) {
                    // Is an even number?
                    if (iCount % 2 == 0) {
                        publish(iCount);
                    }

                    // Set Progress
                    setProgress((iCount * 100) / 20);
                    waitFor(250);
                }

                // Finished
                return true;
            }

            @Override
            protected void process(List<Integer> chunks) {
                // Get Info
                for (int number : chunks) {
                    System.out.println("Found even number: " + number);
                }
            }

            @Override
            protected void done() {
                boolean bStatus = false;
                try {
                    bStatus = get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Finished with status " + bStatus);
            }
        };
    } // End of Method: createWorker()


    /**
     * Wait the given time in milliseconds
     * @param iMillis
     */
    private void waitFor (int iMillis) {
        try {
            Thread.sleep(iMillis);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    } // End of Method: waitFor()


    public static void main(String[] args) {
        // Create the worker
        Loader l = new Loader();
        SwingWorker work = l.createWorker();
        work.execute();

        // Wait for it to finish
        while (!work.isDone()) {
            // Show Progress
            try {
                int iProgress = work.getProgress();
                System.out.println("Progress %" + iProgress);
                Thread.sleep(500);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        } // End of Loop: while (!work.isDone())
    } // End of: main()
    
    
} // End of Class definition

