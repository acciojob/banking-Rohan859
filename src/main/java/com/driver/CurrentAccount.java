package com.driver;

public class CurrentAccount extends BankAccount
{
    String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception
    {

        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000.0);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000.0)
        {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception
    {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception


        int n=this.tradeLicenseId.length();

        for(int i=0;i<n;i++)
        {
            //checking upperCase or not
            char ch=this.tradeLicenseId.charAt(i);
            if(!(ch >= 65 && ch <= 90))
            {
                throw new Exception("Valid License can not be generated");
            }

            //check two consecutive characters are same or not

            if(i > 0 && this.tradeLicenseId.charAt(i) == this.tradeLicenseId.charAt(i - 1)) // Checking consecutive with previous
            {
                throw new Exception("Valid License can not be generated");
            }
            if(i < n - 1 && this.tradeLicenseId.charAt(i) == this.tradeLicenseId.charAt(i + 1)) // Checking consecutive  with next
            {
                throw new Exception("Valid License can not be generated");
            }

        }



        int[] count = new int[26]; // frequency of UpperCase alphabet
        for(int j = 0; j < n; j++)
        {
            count[(int)(this.tradeLicenseId.charAt(j) - 'A')]++; // Count frequency
        }

        for(int j = 0; j < 26; j++)
        {
            if(count[j] > (n + 1)/2) // If count of any character is more than (n+1)/2, throw exception
            {
                throw new Exception("Valid License cannot be generated");
            }
        }
    }

}
