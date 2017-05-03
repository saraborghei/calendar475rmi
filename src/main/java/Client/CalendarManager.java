/**
 * Created by sarab on 5/1/2017.
 */
package Client;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeSet;

public interface CalendarManager extends Remote {
    boolean newCalendar(String name) throws RemoteException;
    TreeSet listUsers() throws RemoteException;
}
