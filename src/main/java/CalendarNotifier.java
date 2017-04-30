/**
 * Created by sarab on 4/30/2017.
 */

import java.util.concurrent.PriorityBlockingQueue;
import java.util.Calendar;
/**
 * Checks event queue for now starting events
 */
public class CalendarNotifier implements Runnable {

    private final CalendarImpl _server;
    private static final int SLEEP = 200;
    private long current;

    public CalendarNotifier(CalendarImpl server) {
        _server = server;
        current = currentTime();
    }

    /**
     * Start checking
     */
    public void run() {
        while (_server.running()) {
            PriorityBlockingQueue<Event> events = _server.getUpcomingEvents();
            // remove all events before ct
            Event event = events.peek();
            boolean found = false;
            while (event != null && !found) {
                if (current > event.getBegin().getTime()) {
                    events.poll();
                    event = events.peek();
                } else {
                    found = true;
                }
            }

            // notifies server if events happen
            boolean finished = false;
            while (event != null && !finished) {
                long abs = event.getBegin().getTime() - current;
                if (abs < SLEEP) {
                    // notify
                    events.poll();
                    _server.notify(event);
                    event = events.peek();
                } else {
                    finished = true;
                }
            }

            current = currentTime();

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * returns current time
     */
    public long currentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getTime();
    }
}