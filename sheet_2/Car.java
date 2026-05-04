package sheet_2;


public class Car implements Display {
    private int weight;
    private String colour;
    private int horsepower;


    public Car(int weight, String colour, int horsepower) {
        this.weight = weight;
        this.colour = colour;
        this.horsepower = horsepower;
    }
    private int getWeight() {
        return weight;
    }
    private int getHorsepower() {
        return horsepower;
    }
    private String getColour() {
        return this.colour;
    }

    public void print() {
        System.out.println("Car weight: " + this.getWeight() + " kg");
        System.out.println("Car colour: " + this.getColour());
        System.out.println("Car horsepower: " + this.getHorsepower() + " hp");
    }
}