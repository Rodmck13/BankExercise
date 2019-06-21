package Bank;

public class Transactions {
    private int id_Tran;
    private String AccNum_Tran;
    private String Date_Tran;
    private String Type_Tran;
    private double Amount_Tran;
    private String Desc_Tran;

    public Transactions(int id, String num, String date, String type, double amount, String desc){
        id_Tran = id;
        AccNum_Tran = num;
        Date_Tran = date;
        Type_Tran = type;
        Amount_Tran = amount;
        Desc_Tran = desc;
    }



    public int getId_Tran() {
        return id_Tran;
    }

    public String getAccNum_Tran() {
        return AccNum_Tran;
    }

    public String getDate_Tran() {
        return Date_Tran;
    }

    public String getType_Tran() {
        return Type_Tran;
    }

    public double getAmount_Tran() {
        return Amount_Tran;
    }

    public String getDesc_Tran() {
        return Desc_Tran;
    }
}
