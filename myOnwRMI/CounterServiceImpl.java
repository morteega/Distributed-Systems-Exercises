package myOnwRMI;

import java.rmi.*;
import java.rmi.server.*;

public class CounterServiceImpl extends UnicastRemoteObject implements CounterService{
    private double counter;

    public CounterServiceImpl() throws RemoteException{
        super();
        this.counter=0;
    }
    @Override
    public double getCounter() throws RemoteException{
        System.out.println("Counter GetValue requested...\n");
        return this.counter;
    }
    @Override
    public void increaseCounter(double number) throws RemoteException{
        this.counter+=number;
        System.out.println("Counter Increase requested...\n");
    }
    @Override
    public void resetCounter() throws RemoteException{
        this.counter=0;
        System.out.println("Counter Reset requested...\n");
    }
}
