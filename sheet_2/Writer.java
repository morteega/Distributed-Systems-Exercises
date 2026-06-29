

import java.io.*;
import java.util.*;

public class Writer {
    public static void main(String[] args) {
        List<Display> displayable = new ArrayList<Display>();
        displayable.add(new Car(1500, "red", 120));
        displayable.add(new Book("The Lord of the Rings", "English", 20.0f, "J.R.R. Tolkien", 123456789));
        displayable.add(new Publication("The New York Times", "English", 2.5f));
        try{
            FileOutputStream outFile= new FileOutputStream("sheet_2/inputfromarrray.txt");
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            for(int i=0; i< displayable.size(); i++){
                out.writeObject(displayable.get(i));
            }
            out.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}
