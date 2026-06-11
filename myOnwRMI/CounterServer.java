package myOnwRMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CounterServer {
    public static void main(String[] args){
        try{
            LocateRegistry.createRegistry(8080);
            CounterServiceImpl counterServer= new CounterServiceImpl();
            Naming.rebind("rmi://localhost:8080/counter",counterServer);
            System.out.println("Counter Started\n");
        }catch(RemoteException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
