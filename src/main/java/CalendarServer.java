import java.rmi.Naming;

/**
 * Created by sarab on 4/30/2017.
 */

public class CalendarServer {
    public CalendarServer(String name) {
        try {
            System.setSecurityManager(new SecurityManager());
            System.out.println("Server: Registering new Calendar");
            CalendarImpl remote = new CalendarImpl();
            Naming.rebind(name, remote);
        } catch (Exception e) {
            System.out.println("Server: Failed to register new calendar: " + e);
        }
    }
}