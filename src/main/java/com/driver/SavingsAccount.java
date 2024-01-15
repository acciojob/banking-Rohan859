package com.driver;

import java.time.Year;
import java.util.HashMap;

public class SavingsAccount extends BankAccount
{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate)
    {
        // minimum balance is 0 by default
        super(name,balance,0.0);
        this.maxWithdrawalLimit=maxWithdrawalLimit;
        this.rate=rate;
    }
    public void withdraw(double amount) throws Exception
    {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount>this.maxWithdrawalLimit)
        {
            throw new Exception("Maximum Withdraw Limit Exceed");
        }
        System.out.println(this.getBalance());


        HashMap<String,Double>myDb=this.getAccDb();
        String myAccNo=this.getAccountNumber();

        if(amount>myDb.get(myAccNo))
        {
            throw new Exception("Insufficient Balance");
        }


        myDb.put(myAccNo,myDb.get(myAccNo)-amount);

        System.out.println("INR. "+amount+" is withdrawn from your account "+myAccNo);
        System.out.println("Your current available balance is "+myDb.get(myAccNo));
    }

    public double getSimpleInterest(int years)
    {
        // Return the final amount considering that bank gives simple interest on current amount
        return (this.getAccDb().get(this.getAccountNumber())*this.rate*years)/100;
    }

    public double getCompoundInterest(int times, int years)
    {
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double x=this.rate/times;
        double p=this.getAccDb().get(this.getAccountNumber());
        double finalAmount=p*Math.pow(1+x,years);
        return finalAmount;
    }

}
