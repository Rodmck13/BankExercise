package Bank;
import java.util.Scanner;
import java.util.InputMismatchException;
class DataRead {
    Scanner myObj = new Scanner(System.in);
    public int inputInt(){
        try {
            int num = myObj.nextInt();
            return num;
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage()); //try to find out specific reason.
        }

        return 0;
    }
    public double inputDouble(){
        double num = myObj.nextDouble();
        return num;
    }
    public String inputLine(){
        String cad = myObj.next();
        return cad;
    }

}