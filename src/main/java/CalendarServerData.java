import java.io.*;
import java.util.HashMap;

/**
 * Created by sarab on 5/1/2017.
 */

public class CalendarServerData implements Serializable {

    private static final long serialVersionUID = 995624507044214456L;
    private static final String FILE = "calendar.dat";

    private final HashMap<String, Event> _events;

    public CalendarServerData(HashMap<String, Event> events) {
        _events = events;
    }

    public HashMap<String, Event> getEvents() {
        return _events;
    }

    /**
     * read data from FILE
     */
    public static CalendarServerData load() {
        if ((new File(FILE)).exists()) {
            try {
                FileInputStream fin = new FileInputStream(FILE);
                ObjectInputStream ois = new ObjectInputStream(fin);
                CalendarServerData calendarData = (CalendarServerData) ois.readObject();
                ois.close();
                return calendarData;
            } catch (Exception e) {
                System.err.println("Can't load " + FILE);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * save data in FILE
     */
    public static boolean save(HashMap<String, Event> events) {

        try {
            FileOutputStream fout = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(new CalendarServerData(events));
            oos.close();
            return true;
        } catch (Exception e) {
            System.err.println("Can't save to " + FILE);
            e.printStackTrace();
            return false;
        }
    }
}