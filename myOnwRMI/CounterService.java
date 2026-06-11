package myOnwRMI;

import java.rmi.*;


public interface CounterService extends Remote{
    public void increaseCounter(double number) throws RemoteException;
    public double getCounter() throws RemoteException;
    public void resetCounter() throws RemoteException;
}
