import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class THClient {

    public static void main(String[] args) {
        try {
            THInterface th = (THInterface) Naming.lookup("//localhost/THInterface");

            if (args.length == 2 && args[0].equals("list")) {
                System.out.println(th.list());
            } else if (args.length == 5 && args[0].equals("book")) {
                System.out.println(th.book(args[2], Integer.parseInt(args[3]), args[4]));
            } else if (args.length == 2 && args[0].equals("guests")) {
                System.out.println(th.guests());
            } else if (args.length == 5 && args[0].equals("cancel")) {
                System.out.println(th.cancel(args[2], Integer.parseInt(args[3]), args[4]));
            }
            else {
                System.out.println("Usage options:\n");
                System.out.println("java THClient list <hostname>");
                System.out.println("java THClient book <hostname> <type> <number> <name>");
                System.out.println("java THClient guests <hostname>");
                System.out.println("java THClient cancel <hostname> <type> <number> <name>");
                System.exit(0);
            }
        }
        catch (MalformedURLException murle) {
            System.out.println();
            System.out.println(
                    "MalformedURLException");
            System.out.println(murle);
        }
        catch (RemoteException re) {
            System.out.println();
            System.out.println(
                    "RemoteException");
            System.out.println(re);
        }
        catch (NotBoundException nbe) {
            System.out.println();
            System.out.println(
                    "NotBoundException");
            System.out.println(nbe);
        }
        catch (
                java.lang.ArithmeticException
                        ae) {
            System.out.println();
            System.out.println(
                    "java.lang.ArithmeticException");
            System.out.println(ae);
        }
    }
}
