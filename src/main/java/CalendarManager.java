/**
 * Created by sarab on 4/29/2017.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Container for all server data
 * Provides methods to save to file and load from file
 */
public class CalendarManager implements Serializable {
    private static final long serialVersionUID = 3409820738914803592L;
    private static final String FILE = "calendar.dat";

    private final HashMap<Long, Event> _events;
    private final PriorityBlockingQueue<Event> _upcomingEvents;
    private final HashMap<String, ArrayList<Event>> _userEvents;

    public CalendarManager(HashMap<Long, Event> events, PriorityBlockingQueue<Event> upcomingEvents, HashMap<String, ArrayList<Event>> userEvents) {
        _events = events;
        _upcomingEvents = upcomingEvents;
        _userEvents = userEvents;
    }

    public PriorityBlockingQueue<Event> getUpcomingEvents() {
        return _upcomingEvents;
    }

    public HashMap<Long, Event> getEvents() {
        return _events;
    }

    public HashMap<String, ArrayList<Event>> getUserEvents() {
        return _userEvents;
    }

    /**
     * load data from file
     */
    public static CalendarManager load() {
        if ((new File(FILE)).exists()) {
            try {
                FileInputStream fin = new FileInputStream(FILE);
                ObjectInputStream ois = new ObjectInputStream(fin);
                CalendarManager calendarData = (CalendarManager) ois.readObject();
                ois.close();
                return calendarData;
            } catch (Exception e) {
                System.err.println("Can't load data file.");
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * save data in file
     */
    public static boolean save(HashMap<Long, Event> events, PriorityBlockingQueue<Event> upcomingEvents, HashMap<String, ArrayList<Event>> userEvents) {
        try {
            FileOutputStream fout = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(new CalendarManager(events, upcomingEvents, userEvents));
            oos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}