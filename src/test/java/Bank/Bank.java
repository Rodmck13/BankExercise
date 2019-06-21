package Bank;
import java.util.ArrayList;
import java.util.Iterator;

public class Bank {
    DataRead read = new DataRead();

    ArrayList<Account> DataArray = new ArrayList<Account>();
    int opc;
    String AccNumber;
    String AccPin;
    String AccID ;
    String FName="";
    String LName="";
    Bank(){
        MainMenu();
    }
    public void MainMenu(){
        System.out.println("Welcome to UnoBank");
        System.out.println("Select an option");
        System.out.println("1) Create a new account");
        System.out.println("2) Log in with your account");
        opc = read.inputInt();
        switch(opc) {
            case 1:
                NewAccount();
                break;
            case 2:
                LogIn();
                break;
            default:
                System.out.println("default");
        }
    }
    public void LogIn()
    {
        System.out.println("{Log In}");
        System.out.println("Account Number: ");
        String AccNumber_login = read.inputLine();
        System.out.println("Password: ");
        String AccPin_login = read.inputLine();
        for (int i = 0; i < DataArray.size(); i++) {
           if(AccNumber_login.equals(DataArray.get(i).getAcc_number()) && AccPin_login.equals(DataArray.get(i).getAcc_pin()))
           {
               System.out.println("Login satisfactorio");
               AccountMenu(i);
           }else{
               System.out.println("Algo salio mal, intentelo nuevamente");
               MainMenu();
           }
        }
    }
    public void AccountMenu(int acc){
        System.out.println("Welcome to Your account, "+DataArray.get(acc).getAcc_Fname());
        System.out.println("Select an option");
        System.out.println("1) Make a deposit.");
        System.out.println("2) Make a withdraw");
        System.out.println("3) Check your current balance.");
        System.out.println("4) Banking movements");
        System.out.println("5) Close account :(");
        int opc_acc = read.inputInt();
        switch(opc_acc) {
            case 1:
                MakeDeposit(acc);
                break;
            case 2:
               MakeWithdraw(acc);
                break;
            case 3:
                CheckCurrentBalance(acc);
                break;
            case 4:
                BankingMovements(acc);
                break;
            case 5:
                CloseAccount(acc);
            default:
                System.out.println("default");
        }
    }
    public void NewAccount()
    {
        System.out.println("{Create a new account}");
        System.out.println("Account holder’s first name");
        FName = read.inputLine();
        System.out.println("Account holder’s last name");
        LName = read.inputLine();
        System.out.println("A Unique account number");
        AccNumber = read.inputLine();
        System.out.println("Account pin (four digit code)");
        AccPin = read.inputLine();
        System.out.println("Account Holders ID Number (SSN, Voter Card ID)");
        AccID = read.inputLine();
        if(CreateAccount(FName, LName, AccNumber, AccPin, AccID)){
            System.out.println("Usuario agregado satisfactoriamente con el número de cuenta: "+AccNumber);
            MainMenu();
        }
        else{
            System.out.println("Error al crear cuenta1" +
                    "");
            MainMenu();
        }
        System.out.println();
        System.out.println();
        MainMenu();
    }
    public boolean CreateAccount(String FName,String LName,String AccNumber,String AccPin,String AccID){
        for (int i = 0; i < DataArray.size(); i++) {
            if(AccNumber.equals(DataArray.get(i).getAcc_number()))
                {
                    System.out.println("Number account already exist");
                    return false;
                }
            }
        DataArray.add(new Account(  FName, LName, AccNumber, AccPin, AccID));
        return true;
    }
    public void MakeDeposit(int acc){
        System.out.println("Make a deposit to the account"+DataArray.get(acc).getAcc_number());
        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
        System.out.println("Enter the amount to deposit: ");
        String amount = read.inputLine();
        System.out.println("Enter the Description of deposit : ");
        String description = read.inputLine();


        //Try catch para castear
        double amountDouble=Double.parseDouble(amount);
        DataArray.get(acc).deposit(amountDouble, "Deposit", description);
        AccountMenu(acc);
    }
    public void MakeWithdraw(int acc){

        System.out.println("Make a withdraw to the account"+DataArray.get(acc).getAcc_number());
        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
        System.out.println("Enter the amount to withdraw: ");
        String amount = read.inputLine();
        System.out.println("Enter the Description of withdraw : ");
        String description = read.inputLine();
        //Try catch para castear
        double amountDouble=Double.parseDouble(amount);

        DataArray.get(acc).withdraw(amountDouble, "Withdraw", description);


        AccountMenu(acc);

    }
    public void CheckCurrentBalance(int acc){

        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
        System.out.println("Current Balance: $"+DataArray.get(acc).GetBalance());

        AccountMenu(acc);

    }
    public void CloseAccount(int acc){
        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
        System.out.println("Current Balance: $"+DataArray.get(acc).GetBalance());
        System.out.println("Are you sure you want to delete this account? y/n");

        //validar entrada
        String yn = read.inputLine();
        if(yn.equals("y")){
            DataArray.remove(acc);
            MainMenu();
        }
        else
            {
              AccountMenu(acc);
            }

    }
    public void BankingMovements(int acc) {
        ArrayList a = DataArray.get(acc).GetTransactions();
        for(int i=0;i<a.size();i++){


        }
        AccountMenu(acc);
    }
    public static void main(String[] args) {
            Bank a=new Bank();
       }
}
