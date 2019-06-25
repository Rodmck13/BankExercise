package Bank;
import java.util.Scanner;
import java.util.InputMismatchException;
class DataRead {
    Scanner myObj = new Scanner(System.in);

    public int inputIntMain(){
        int number;
        do {
            while (!myObj.hasNextInt()) {
                System.out.println("Invalid option, please try again");
                myObj.next();
            }
            number = myObj.nextInt();
        } while (number <= 0 || number > 2);
       return number;
    }
    public int inputRate(){
        int number;
        do {
            while (!myObj.hasNextInt()) {
                System.out.println("Invalid option, please try again");
                myObj.next();
            }
            number = myObj.nextInt();
        } while (number <= 0 || number > 4);
        return number;

    }
    public int inputIntAccount(){
        int number;
        do {

            while (!myObj.hasNextInt()) {
                System.out.println("Invalid option, please try again");
                myObj.next();
            }
            number = myObj.nextInt();
        } while (number <= 0 || number > 7);
        return number;
    }
    public String inputLinePin(){
        String cad;
        do {
            System.out.println("Account pin (four digit code)");
             cad = myObj.next();
        }while (cad.length()!=4);

        return cad;
    }
    public double inputDouble(){
        double num = myObj.nextDouble();
        return num;
    }
    public String inputLine(){
        String cad = myObj.next();
        cad.trim();
        return cad;
    }

}