package sheet_2;

public class Publication implements Display {
    private String title;
    private String language;
    private float price;

    public Publication(String title, String language, float price) {
        this.title = title;
        this.language = language;
        this.price = price;
    }
    public void print() {
        System.out.println("Publication title: " + this.title);
        System.out.println("Publication language: " + this.language);
        System.out.println("Publication price: " + this.price + " euros");
    }
    
}
