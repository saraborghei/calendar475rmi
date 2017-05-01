/**
 * Created by sarab on 4/29/2017.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CalendarImpl extends UnicastRemoteObject implements Calendar {
    private static final long serialVersionUID = -8040611803588290632L;
    private final Registry _registry;

    private static String NAME;
    private Iterator<Map.Entry<String, Event>> itr;
    private final HashMap<String, Event> _events;

    public CalendarImpl() throws RemoteException {
        // load server data
        CalendarServerData calendarData = CalendarServerData.load();

        if (calendarData == null) {
            _events = new HashMap<String, Event>();
        } else {
            _events = calendarData.getEvents();
        }

        // Create RMI
        int port = 1;
        _registry = LocateRegistry.createRegistry(port);
        _registry.rebind(NAME, this);

        try {
            String address = (InetAddress.getLocalHost()).toString();
            System.out.println("Server running @ " + address + ":" + port);
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
        while (itr.hasNext() && itr.next().getValue().compareTo(e) > 0) {
            e.setId(id);
            _events.put(id, e);
            return true;
        }
        return false;
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
     * @param user
     * @return a list of user's events
     * @throws RemoteException
     */
    @Override
    public List<Event> listEvents(String user) throws RemoteException {
        return null;
    }
}