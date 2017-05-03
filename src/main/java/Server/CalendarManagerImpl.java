/**
 * Created by sarab on 5/1/2017.
 */
package Server;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.TreeSet;

public class CalendarManagerImpl extends UnicastRemoteObject implements CalendarManager {
    private static final long serialVersionUID = -7038147413769786357L;
    private HashMap<String, CalendarImpl> calendars;

    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     *
     * @throws RemoteException if failed to export object
     * @link RMISocketFactory
     */
    protected CalendarManagerImpl() throws RemoteException {
    }

    public boolean newCalendar(String name) {
        try {
            if (calendars.containsKey(name)) {
                return false;
            } else {
                CalendarImpl cal = new CalendarImpl();
                calendars.put(name, cal);
                Naming.bind("Calendar", cal);
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TreeSet<String> listUsers() throws RemoteException {
        TreeSet<String> users = new TreeSet<String>();
        users.addAll(calendars.keySet());
        return users;
    }
}
