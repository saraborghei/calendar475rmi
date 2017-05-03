/**
 * Created by sarab on 4/29/2017.
 */
package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Callendar extends Remote {

    /**
     * add events to database of the server
     * server change the event id and assign a new id
     *
     * @param e the event to add
     * @return returns the id of the event
     * @throws RemoteException
     */
    boolean addEvent(Event e) throws RemoteException;

    /**
     * remove an event from the database
     *
     * @param id the id of the event
     * @return true if the event was successfully removed
     * @throws RemoteException
     */
    boolean removeEvent(String id) throws RemoteException;

    /**
     * @return a list of user's events
     * @throws RemoteException
     */
    List<Event> listEvents() throws RemoteException;
}