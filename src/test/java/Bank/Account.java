package Bank;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
public class Account
{
    private String acc_number;
    private String acc_Fname;
    private String acc_Lname;
    private String acc_pin;
    private String acc_HolIDNumber;
    private double Current_balance;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();



    ArrayList<Transactions> TransactionArray = new ArrayList<Transactions>();

    public Account( String FName,String LName,String AccNumber,String AccPin,String AccID){

        Current_balance = 0.0;
        acc_Fname = FName;
        acc_Lname = LName;
        acc_number = AccNumber;
        acc_pin = AccPin;
        acc_HolIDNumber = AccID;

    }

    public void CreateTransaction(String type, String desc, double amount){
        if(TransactionArray.size()== 5){
            TransactionArray.remove(0);
            TransactionArray.add(new Transactions(0,  acc_number,  dateFormat.format(date),  type,  amount, desc));

        }else{
            TransactionArray.add(new Transactions(0,  acc_number,  dateFormat.format(date),  type,  amount, desc));
        }
    }
    public ArrayList GetTransactions()
    {
        ArrayList  a = TransactionArray;
        return a;
    }
    public String GetSpecificTransactions(double ValorEuro, double ValorPeso, int CurrentRate)
    {
        String a="";
        switch(CurrentRate) {
            case 0:
                for (int i=0;i<TransactionArray.size();i++){
                    a += "[Account No. : "+TransactionArray.get(i).getAccNum_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDate_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDesc_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getType_Tran() +"]"+"[ $"
                            +TransactionArray.get(i).getAmount_Tran() +" dlls ]\n";
                }
                System.out.println("* The amounts are based on dollar, to change it go to option 5 of the menu");

                break;
            case 1:
                for (int i=0;i<TransactionArray.size();i++){
                    a += "[Account No. : "+TransactionArray.get(i).getAccNum_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDate_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDesc_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getType_Tran() +"]"+"[ €"
                            +TransactionArray.get(i).getAmount_Tran()*ValorEuro +" EUR]\n";
                }
                System.out.println("* The amounts are based on EUR, to change it go to option 5 of the menu");
                break;
            case 2:
                for (int i=0;i<TransactionArray.size();i++){
                    a += "[Account No. : "+TransactionArray.get(i).getAccNum_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDate_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getDesc_Tran() +"]"+"[ "
                            +TransactionArray.get(i).getType_Tran() +"]"+"[ $"
                            +TransactionArray.get(i).getAmount_Tran()*ValorPeso +" MXN]\n";
                }
                System.out.println("* The amounts are based on MXN, to change it go to option 5 of the menu");
                break;
        }




      /*  for (int i=0;i<TransactionArray.size();i++){
            a += "[Account No. : "+TransactionArray.get(i).getAccNum_Tran() +"]"+"[ "
                    +TransactionArray.get(i).getDate_Tran() +"]"+"[ "
                    +TransactionArray.get(i).getDesc_Tran() +"]"+"[ "
                    +TransactionArray.get(i).getType_Tran() +"]"+"[ $"
                    +TransactionArray.get(i).getAmount_Tran() +"]\n";
        }*/
        System.out.println("[Current Balance: $"+Current_balance+"]");

        return a;
    }
    public String getAcc_number() {
        return acc_number;
    }


    public String getAcc_Fname() {
        return acc_Fname;
    }
    public String getAcc_Lname() {
        return acc_Lname;
    }


    public String getAcc_pin() {
        return acc_pin;
    }

    public void deposit(double amount, String type, String desc){
        CreateTransaction(type, desc, amount);
        Current_balance = Current_balance + amount;
    }

    public void withdraw(double amount, String type, String desc){
        if (Current_balance>=amount){
            CreateTransaction(type, desc, amount);
            Current_balance = Current_balance - amount;
        }
        else{
            System.out.println("Insufficient funds");
        }
    }
    public double GetBalance(){
        return Current_balance;
    }
}
