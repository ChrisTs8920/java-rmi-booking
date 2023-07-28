public interface THInterface extends java.rmi.Remote {

    String list() throws java.rmi.RemoteException;

    String book(String type, int number, String name) throws java.rmi.RemoteException;

    String guests() throws java.rmi.RemoteException;

    String cancel(String type, int number, String name) throws java.rmi.RemoteException;
}
