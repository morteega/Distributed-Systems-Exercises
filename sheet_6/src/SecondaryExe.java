

public class SecondaryExe {

    private static SecondarySystem secondarySystem;

    public static void main(String[] args){
        secondarySystem = new SecondarySystem();
        secondarySystem.run(args[0]);
    }
}
