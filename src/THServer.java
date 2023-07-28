import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class THServer {

    public THServer () {
        try {
            THInterface th = new THImpl();
            // Bind this object instance to the name "RmiServer"
            Naming.rebind("//localhost/THInterface", th);
            System.out.println("PeerServer bound in registry");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String[] args) {
        System.out.println("RMI server started");
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        //Instantiate RmiServer
        new THServer();
    }
}
