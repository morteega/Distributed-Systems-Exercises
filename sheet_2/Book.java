package sheet_2;

public class Book extends Publication {
    private String author;
    private int isbn;

    public Book(String title, String language, float price, String author, int isbn) {
        super(title, language, price);
        this.author = author;
        this.isbn = isbn;
    }
    public void print() {
        super.print();
        System.out.println("Book author: " + this.author);
        System.out.println("Book ISBN: " + this.isbn);
    }
}
