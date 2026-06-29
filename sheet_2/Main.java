
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Display> displayable = new ArrayList<Display>();
        displayable.add(new Car(1500, "red", 120));
        displayable.add(new Book("The Lord of the Rings", "English", 20.0f, "J.R.R. Tolkien", 123456789));
        displayable.add(new Publication("The New York Times", "English", 2.5f));

        for (Display d : displayable) {
            d.print();
            System.out.println();
        }
    }
}
