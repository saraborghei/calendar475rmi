/**
 * Created by sarab on 4/29/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarClient {
    CalendarImpl calendar;

    public CalendarClient(String name) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(name);
        calendar = (CalendarImpl) (registry.lookup(name));
    }

    public void run() {
        System.out.print("Calendar client.\n"
                + "add: <begin_date>;<end_date>;<description> - to add an event\n"
                + "remove: <id> - to remove an event\n"
                + "quite - to close the client\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String line = br.readLine();
                try {
                    if (line.startsWith("add:")) {
                        // Add a new event
                        String params[] = getParameters(line, 3);
                        boolean success = calendar.addEvent(parseEvent(
                                params[0], params[1], params[2]));
                        if (success) {
                            System.out.println("Event removed.");
                        } else {
                            System.out.println("Can't remove the event.");
                        }
                        System.out.println("Event created.");
                    } else if (line.startsWith("remove:")) {
                        // Remove an event
                        String params[] = getParameters(line, 1);
                        boolean success = calendar.removeEvent(params[0]);
                        if (success) {
                            System.out.println("Event removed.");
                        } else {
                            System.out.println("Can't remove the event.");
                        }
                    } else if (line.equals("quite")) {
                        break;
                    } else {
                        System.err.println("Unknown command.");
                    }
                } catch (ParseException e) {
                    System.err.println("Invalid date format.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid arguments.");
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Closed.");
    }

    /**
     * Parse an event from strings
     */
    public Event parseEvent(String begin, String end, String description) throws ParseException {
        //String[] users = user.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(sdf.parse(begin));

        Calendar endTime = Calendar.getInstance();
        beginTime.setTime(sdf.parse(end));

        return new Event(beginTime.getTime(), endTime.getTime(), description);
    }

    /**
     * Parse list of parameters from string
     */
    public String[] getParameters(String line, int length) throws IllegalArgumentException {
        String[] parts = line.split(": ");
        if (parts.length == 2) {
            String[] params = parts[1].split(";");
            if (params.length == length) {
                return params;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Callable from server, if registered event happens
     */
    public void call(Event e) throws RemoteException {
        System.out.println("Notification from server");
        System.out.println(e);
    }
}