package sheet_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Reader {
    public static void main(String args[]) {
        List<Display> displayable = new ArrayList<>();
        Car car = new Car(1500, "red", 120);
        Publication publication = new Publication("Distributed Systems", "English", 50.0f);
        Book book = new Book("Distributed Systems", "English", 50.0f, "Andrew S. Tanenbaum", 123456789);
        displayable.add(book);
        displayable.add(publication);
        displayable.add(car);
        try{
            FileInputStream inFile= new FileInputStream("sheet_2/inputfromarrray.txt");
            ObjectInputStream in = new ObjectInputStream(inFile);
            for(int i=0; i<displayable.size(); i++){
                Display displyedObject=(Display) in.readObject();
                displyedObject.print();
                System.out.println();
            }
            in.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
