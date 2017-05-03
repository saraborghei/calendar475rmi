/**
 * Created by sarab on 4/30/2017.
 */
package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalendarServer {
    public static void main(String argv[]) {
        try {
            CalendarServer server = new CalendarServer();
            System.setSecurityManager(new SecurityManager());
            System.out.println("Server: Registering Calendar Manager");
            CalendarManagerImpl remote = new CalendarManagerImpl();
            Naming.bind("CalendarManager", remote);
            //Registry registry = LocateRegistry.getRegistry();
            //registry.rebind("CalendarManager", remote);
            System.out.println("Server: Ready ...");
        } catch (Exception e) {
            System.out.println("Server: Failed to register new calendar: " + e);
        }
    }
}