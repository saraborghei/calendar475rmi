import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by sarab on 5/1/2017.
 */
public class Main {
    private static final String USAGE = "Wrong use!";
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("New user? (y/n) ");
            boolean op = false;
            while (!op) {
                String user = keyboard.next();
                switch (user.toLowerCase()) {
                    case "y":
                        System.out.print("Please enter your username: ");
                        String username = keyboard.next();
                        new CalendarServer(username);
                        op = true;
                        break;
                    case "n":
                        op = true;
                        break;
                    default:
                        System.out.print("Please enter y/n: ");
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(USAGE);
        } catch (NumberFormatException e) {
            System.err.println(USAGE);
        } catch (IllegalArgumentException e) {
            System.err.println(USAGE);
        } /*catch (RemoteException e) {
            System.err.println("Can't connect. (RemoteException)");
        } catch (NotBoundException e) {
            System.err.println("Can't connect. (NotBoundException)");
        }*/
    }
}