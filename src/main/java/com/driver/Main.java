package com.driver;

public class Main {
    public static void main(String[] args) throws Exception
    {
        //BankAccount obj=new BankAccount("SBI",87.20,9.9);



        SavingsAccount obj=new SavingsAccount("SBI",8000,5000,3);
        System.out.println(obj.getAccountNumber());
        System.out.println(obj.getAccDb());
        obj.withdraw(5000);

        System.out.println("simple interest = "+obj.getSimpleInterest(3));
        System.out.println("compound interest = "+obj.getCompoundInterest(2,3));
    }
}