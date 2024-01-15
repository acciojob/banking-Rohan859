package com.driver;

import java.util.HashMap;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

   private HashMap<String,Double>accDb;
   private String accountNumber;

   private boolean isAccountCreated;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public HashMap<String, Double> getAccDb() {
        return accDb;
    }

    public void setAccDb(HashMap<String, Double> accDb) {
        this.accDb = accDb;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isAccountCreated() {
        return isAccountCreated;
    }

    public void setAccountCreated(boolean accountCreated) {
        isAccountCreated = accountCreated;
    }

    public BankAccount(String name, double balance, double minBalance)
    {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
        this.accDb=new HashMap<>();
        this.accountNumber="";


        try {
            this.accountNumber=this.generateAccountNumber(3,25);
        }
        catch (Exception e)
        {
            System.out.println("Account number can not be generated");
        }

    }


    public String generateAccountNumber(int digits, int sum) throws Exception
    {
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(isAccountCreated==false)
        {
            StringBuilder sb=new StringBuilder();

            if(sum==0)
            {
                while (digits-->0)
                {
                    sb.append(0);
                }
                accDb.put(sb.toString(), this.balance);

                this.accountNumber=sb.toString();
                this.isAccountCreated=true;

                return sb.toString();
            }

            int arr[]=new int[10];
            int count=0;

            for(int i=9;i>=1 && sum!=0;i--)
            {
                if (sum >= 0 && sum <= 9)
                {
                    arr[sum] += 1;
                    break;
                }
                int freq = sum / i;
                arr[i]=freq;
                sum -= (i * freq);
            }



            for(int i=0;i<10;i++)
            {
                if(arr[i]==0)
                {
                    continue;
                }

                while (arr[i]-->0)
                {
                    sb.append(i);
                    count++;
                }
            }

            if(count==digits)
            {
                accDb.put(sb.toString(), this.balance);

                this.accountNumber=sb.toString();
                this.isAccountCreated=true;
                return sb.toString();
            }

            if(count<digits)
            {
                int k=digits-count;
                while (k-->0)
                {
                    sb.append(0);
                }

                String temp=sb.toString();
                if(accDb.containsKey(temp))
                {
                    return "Account already Exist";
                }
                accDb.put(sb.toString(), this.balance);

                this.accountNumber=temp;
                this.isAccountCreated=true;
                return sb.toString();
            }

            throw new Exception("Account Number can not be generated");
        }
        else
        {
            return "Account already created";
        }


        }



    public void deposit(double amount)
    {
        //add amount to balance
        accDb.put(this.accountNumber,accDb.getOrDefault(this.accountNumber,0.0)+amount);
        System.out.println("INR. "+amount+" is deposited in your account number "+this.accountNumber);
        System.out.println("Your balance is "+accDb.get(this.accountNumber));


    }

    public void withdraw(double amount) throws Exception
    {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        double myBalance=accDb.get(this.accountNumber);

        if(amount>myBalance || amount<0.0)
        {
           throw new Exception("your available balance is "+accDb.get(this.accountNumber)+" and your withdraw amount is "+amount+" your withdraw amount is high");
        }

       if((myBalance-amount)<this.minBalance)
       {
           throw new Exception("Insufficient Balance");
       }


        accDb.put(this.accountNumber,accDb.get(this.accountNumber)-amount);
        System.out.println("INR. "+amount+" is withdraw from your account "+this.accountNumber);
        System.out.println("Your available balance in your account is "+accDb.get(this.accountNumber));


    }

}