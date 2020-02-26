package themo;

import java.text.DecimalFormat;
import java.util.*; 

public class Expense
{
    private String month;
    private String day;
    private String year;
    private double amount;
    private String item;
    //private String date;
    
    
    private static final DecimalFormat dollar = new DecimalFormat("0.00");

    public Expense(String month, String day,String year, String item, double amount)
    {
        //this.date = date;
        this.month = month;
        this.day = day;
        this.year = year;
        this.item = item;
        this.amount = amount;
        
        /*StringTokenizer st=new StringTokenizer(date, "\t");
        this.month = st.nextToken();
        this.day = st.nextToken();
        this.year = st.nextToken();*/
    }
    @Override
    public String toString()
    {
        String finalDate =this.month.equals("null")? "":this.month+" ";
        finalDate =this.day.equals("null")? "":this.day+" ,";
        finalDate =this.year.equals("null")? "":this.year;
        return finalDate + "\t" + item + "\t\t" + dollar.format(amount) + "\n";
    }
    public boolean equals(Expense other)
    {
        return (item.equals(other.getItem())&&this.getDate().equals(other.getDate())&&amount==other.getAmount());

    }
    public void setItem(String item){
        
        this.item=item;
    }
    /*public void setDate(String date){
        this.date=date;
    }*/
    public void setAmount(double amount){
        this.amount= amount;
    }
    /*public void setYear(String year){
        this.year=year;
    }*/
    public String getItem()
    {
        return item;
    }
    public String getDate()
    {
        return month+" "+day+", "+year;
    }
    public double getAmount()
    {
        return amount;
    }
    public String getYear()
    {
        return year;
    }
    public String getMonth()
    {
        return month;
    }
    public String getDay()
    {
        return day;
    }

}