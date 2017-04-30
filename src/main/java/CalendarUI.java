/**
 * Created by sarab on 4/30/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Server UI
 */
public class CalendarUI implements Runnable {
    private final CalendarImpl _server;

    public CalendarUI(CalendarImpl server) {
        _server = server;
    }

    public void run() {

        System.out.print("Calendar server:\n"
                + "clean - removes all data from server.\n"
                + "exit - save data and exit.\n");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String line = buffer.readLine();
                // Shutdown the server
                if (line.equalsIgnoreCase("exit")) {
                    _server.close();
                    break;
                } else if (line.equalsIgnoreCase("clean")) {
                    _server.flush();
                } else {
                    System.err.println("Unknown command!");
                }
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connection closed.");
        System.exit(0);
    }
}