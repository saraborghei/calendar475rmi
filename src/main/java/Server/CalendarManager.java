package Server;
/**
 * Created by sarab on 5/1/2017.
 */
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeSet;

public interface CalendarManager extends Remote {
    boolean newCalendar(String name) throws RemoteException, AlreadyBoundException, MalformedURLException;
    TreeSet listUsers() throws RemoteException;
}
