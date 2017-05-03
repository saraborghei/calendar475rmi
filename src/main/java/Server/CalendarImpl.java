package Server; /**
 * Created by sarab on 4/29/2017.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CalendarImpl extends UnicastRemoteObject implements Callendar {
    private static final long serialVersionUID = 5846796844759974799L;
    private final Registry _registry;

    private Iterator<Map.Entry<String, Event>> itr;
    private final HashMap<String, Event> _events;

    public CalendarImpl() throws RemoteException {
        // load server data
        /*CalendarServerData calendarData = CalendarServerData.load();

        if (calendarData == null) {
            _events = new HashMap<String, Server.Event>();
        } else {
            _events = calendarData.getEvents();
        }*/
        _events = new HashMap<String, Event>();

        // Create RMI
        _registry = LocateRegistry.getRegistry();
        _registry.rebind("", this);

        try {
            String address = (InetAddress.getLocalHost()).toString();
            System.out.println("Server running ... ");
        } catch (UnknownHostException e) {
            System.err.println("Can't determine adress.");
        }
    }

    /**
     * add an event
     */
    public boolean addEvent(Event e) {
        itr = _events.entrySet().iterator();
        String id = e.getBegin().toString() + e.getEnd().toString();
        while (itr.hasNext()) {
            if (e.compareTo(itr.next().getValue()) > 0) {
                itr.next();
            } else {
                return false;
            }
        }
        e.setId(id);
        _events.put(id, e);
        return true;
    }

    /**
     * Removes an event by id
     */
    public boolean removeEvent(String id) {
        // Remove from storage
        itr = _events.entrySet().iterator();
        while (itr.hasNext()) {
            if (itr.next().getValue().getId().equals(id)) {
                _events.remove(itr.next());
                return true;
            }
        }
        return false;
    }

    /**
     * @return a list of user's events
     * @throws RemoteException
     */
    public List<Event> listEvents() {
        itr = _events.entrySet().iterator();
        List<Event> eventsList = new LinkedList<Event>();
        while (itr.hasNext()) {
            eventsList.add(itr.next().getValue());
        }
        return eventsList;
    }
}