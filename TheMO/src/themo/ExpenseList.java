package themo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpenseList
{
    private List<Expense> expenses;


    public ExpenseList()
    {
        expenses = new List<Expense>();
    }
    public void add(Expense exp)
    {
        expenses.add(exp);
    }

    public boolean isEmpty()
    {
        return expenses.isEmpty();
    }
    public boolean contains(Expense exp)
    {
        return expenses.contains(exp);
    }
    public Expense first()
    {
        return expenses.first();
    }
    public Expense next()
    {
        return expenses.next();
    }
    public void enumerate()
    {
        expenses.enumerate();
    }

    public double maxExpense()
    {
        double max =0.0, amt;
        Expense exp = expenses.first();
        while (exp!=null)
        {
            amt = exp.getAmount();
            if (amt>max)
                max = amt;
            exp = expenses.next();
        }
        return max;
    }
    public double minExpense()
    {
        double min =Double.MAX_VALUE, amt;
        Expense exp = expenses.first();
        if (exp==null) return 0.0;
        else
        {

            while (exp!=null)
            {
                amt = exp.getAmount();
                if (amt<min)
                    min = amt;
                exp = expenses.next();
            }
        }
        return min;
    }
    public double avgExpense()
    {
        double total = 0.0;
        Expense exp = expenses.first();
        while (exp != null) {
            total+=exp.getAmount();
            exp =expenses.next();
        }
        if(expenses.size()!=0)
            return total/expenses.size();
        else
            return 0;
    }
    public double totalExpense()
    {
        double total = 0.0;
        Expense exp = expenses.first();
        while (exp != null) {
            total+=exp.getAmount();
            exp =expenses.next();
        }
        return total;
    }
    public double amountSpentOn(String expItem)
    {
        double amount = 0.0;
        Expense exp = expenses.first();
        while (exp != null){
            if (exp.getItem().equals(expItem))
                amount+= exp.getAmount();
            exp =  expenses.next();
        }
        return amount;
    }
    public ObservableList<Expense> getObservableList(){
        ObservableList<Expense> expensesObservableList = FXCollections.observableArrayList();
        Expense exp = expenses.first();
        while (exp != null){
            expensesObservableList.add(exp);
            exp =  expenses.next();
        }
        return expensesObservableList;
    }
    public ObservableList<Expense> getExpensesByType(String type){
        ObservableList<Expense> expensesObservableList = FXCollections.observableArrayList();
        Expense exp = expenses.first();
        while (exp != null){
            if(exp.getItem().equals(type))
                expensesObservableList.add(exp);
            exp =  expenses.next();
        }
        return expensesObservableList;
    }
    public ObservableList<Expense> getExpensesByPrice(double price){
        ObservableList<Expense> expensesObservableList = FXCollections.observableArrayList();
        Expense exp = expenses.first();
        while (exp != null){
            if(exp.getAmount()==price)
                expensesObservableList.add(exp);
            exp =  expenses.next();
        }
        return expensesObservableList;
    }
    public ObservableList<Expense> getExpensesByYear(String year){
        ObservableList<Expense> expensesObservableList = FXCollections.observableArrayList();
        Expense exp = expenses.first();
        while (exp != null){
            if(exp.getYear().equals(year))
                expensesObservableList.add(exp);
            exp =  expenses.next();
        }
        return expensesObservableList;
    }


}
