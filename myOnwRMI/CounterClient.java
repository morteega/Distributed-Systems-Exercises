package myOnwRMI;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class CounterClient {
    public static void main(String[] args){
        try{
            CounterService counter= (CounterService)Naming.lookup("rmi://"+args[0]+"/counter");
            System.out.println("Succesfully retrieved the object\n");
            System.out.println("Initial counter value"+counter.getCounter());
            System.out.println("Increasing counter...:\n");
            counter.increaseCounter(3);
            System.out.println("Increasing counter...:\n");
            counter.increaseCounter(2);
            System.out.println("Counter actual value: "+counter.getCounter());
            System.out.println("Resetting counter...:\n");
            counter.resetCounter();
            System.out.println("Counter actual value: "+counter.getCounter());
        }catch(RemoteException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
