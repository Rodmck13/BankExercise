package Bank;
import java.util.ArrayList;


public class Bank {
    DataRead read = new DataRead();
    Boolean incorrect = false;
    int exchange=0; //USD=0, EUR=1, MXN=2;
    ExchangeRequest ExchangeRequest = new ExchangeRequest();
    ArrayList<Account> DataArray = new ArrayList<Account>();
    int opc = -1;
    int opc_acc = -1;
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
        opc = read.inputIntMain();
        switch(opc) {
            case 1:
                NewAccount();
                break;
            case 2:
                LogIn();
                break;
        }
    }
    public void LogIn()
    {
        System.out.println("{Log In}");
        System.out.println();
        System.out.println();
        System.out.println("Account Number: ");
        String AccNumber_login = read.inputLine();
        System.out.println("Password: ");
        String AccPin_login = read.inputLine();
        for (int i = 0; i < DataArray.size(); i++) {
           if(AccNumber_login.equals(DataArray.get(i).getAcc_number()) && AccPin_login.equals(DataArray.get(i).getAcc_pin()))
           {
               System.out.println("Login successful");
               AccountMenu(i);
           }else{
               System.out.println("Login unsuccessful, Please try again");
               MainMenu();
           }
        }
    }
    public void AccountMenu(int acc){
        System.out.println();
        System.out.println("Welcome to Your account, "+DataArray.get(acc).getAcc_Fname());
        System.out.println();
        System.out.println();
        System.out.println("Select an option");
        System.out.println("1) Make a deposit.");
        System.out.println("2) Make a withdraw");
        System.out.println("3) Check your current balance.");
        System.out.println("4) Banking movements");
        System.out.println("5) Exchange Rate");
        System.out.println("6) Close account :(");
        System.out.println("7) Main Menu");
        opc_acc = read.inputIntAccount();
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
                ExchangeRate(acc);
            case 6:
                CloseAccount(acc);
            case 7:
                MainMenu();

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

        AccPin = read.inputLinePin();
        System.out.println("Account Holders ID Number (SSN, Voter Card ID)");
        AccID = read.inputLine();
        if(CreateAccount(FName, LName, AccNumber, AccPin, AccID)){
            System.out.println("User successfully added with account number: "+AccNumber);
            MainMenu();
        }
        else{
            System.out.println("Error creating account" +
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
        double amountDouble=Double.parseDouble(amount);

        DataArray.get(acc).withdraw(amountDouble, "Withdraw", description);


        AccountMenu(acc);

    }
    public void CheckCurrentBalance(int acc){

        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
       ExchangeRequest ER= new ExchangeRequest();
       try{
           ER.sendGet();
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

        switch(exchange) {
            case 0:
                System.out.println("* The amounts are based on dollar, to change it go to option 5 of the menu");
                System.out.println("Current Balance: US$ "+DataArray.get(acc).GetBalance());
                break;
            case 1:
                System.out.println("* The amounts are based on EUR, to change it go to option 5 of the menu");
                System.out.println("Current Balance: €"+DataArray.get(acc).GetBalance()* ER.valorEuroDouble);
                break;
            case 2:
                System.out.println("* The amounts are based on MXN, to change it go to option 5 of the menu");
                System.out.println("Current Balance: $"+DataArray.get(acc).GetBalance()*ER.valorPesoDouble+" MXN.|");
                break;
    }
    AccountMenu(acc);

    }
    public void CloseAccount(int acc){
        System.out.println("| User: "+DataArray.get(acc).getAcc_Fname()+" "+DataArray.get(acc).getAcc_Lname()+"|");
        System.out.println("| Account Number: "+DataArray.get(acc).getAcc_number()+"|");
        System.out.println();
        System.out.println("Current Balance: $"+DataArray.get(acc).GetBalance());
        System.out.println("Are you sure you want to delete this account? y/n");

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
        ExchangeRequest ER= new ExchangeRequest();
        try{
            ER.sendGet();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String a = DataArray.get(acc).GetSpecificTransactions(ER.valorEuroDouble, ER.valorPesoDouble, exchange);
        if(a.length() > 0){
            System.out.println(a);
        }else
        {
            System.out.println("You don't have any movements yet");
        }
        AccountMenu(acc);
    }

    public void ExchangeRate(int acc){

        System.out.println("Change exchange rate");
        System.out.println("Select an option");
        System.out.println("1) USD");
        System.out.println("2) EUR");
        System.out.println("3) MXN");
        System.out.println("4) Cancel");
        opc = read.inputRate();
        switch(opc) {
            case 1:
                exchange =1;
                break;
            case 2:
                exchange =1;
                break;
            case 3:
                exchange =2;
                break;
            case 4:
                AccountMenu(acc);
                break;
        }
        AccountMenu(acc);

    }

    public static void main(String[] args) {
            Bank a=new Bank();
       }
}
