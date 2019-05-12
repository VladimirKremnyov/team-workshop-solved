package bank;

import bank.account.BankAccount;
import bank.account.InterestType;
import bank.exceptions.InsufficientFundsException;
import bank.exceptions.NonExistingBankAccountException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.pow;

public class Bank {
    private Set<BankAccount> accounts;

    public Bank(){
        accounts = new HashSet<>();
    }

    public void createBankAccount(Person owner, double interest, InterestType interestType){
        BankAccount account = new BankAccount(owner,interest,interestType);
        accounts.add(account);
        System.out.println(account.getId());
    }

    public List<String> showOperations(int accountID) throws NonExistingBankAccountException {
        for(BankAccount account : accounts){
            if(accountID == account.getId()){
                return account.getHistory();
            }
        }
        throw new NonExistingBankAccountException(String.format("Bank account %s does not exist.%n",accountID));
    }

    public void addMoney(int accountID, double amount) throws NonExistingBankAccountException {
        BankAccount account = findAccount(accountID);
        account.add(amount);
    }
    public boolean withdrawMoney(int accountID, double amount) throws NonExistingBankAccountException, InsufficientFundsException {
        BankAccount account = findAccount(accountID);
        return account.withdraw(amount);
    }

    public boolean transferMoney(int source, int destination, double amount) throws NonExistingBankAccountException, InsufficientFundsException {
        BankAccount sourceAccount = findAccount(source);
        BankAccount destinationAccount = findAccount(destination);

        return sourceAccount.transfer(destinationAccount,amount);
    }

    public double calculateAmount(int accountID, int months) throws NonExistingBankAccountException {
        BankAccount account = findAccount(accountID);
        double years = ((double) months)/12;
        switch(account.getInterestType()) {
            case SIMPLE:
                return account.getBalance() * account.getInterest() * years;
            case COMPLEX:
                return account.getBalance() * (pow((1 + account.getInterest()), years) - 1);
            default:
                throw new IllegalArgumentException();
        }
    }
    private BankAccount findAccount(int accountID) throws NonExistingBankAccountException {
        for(BankAccount account : accounts){
            if(accountID == account.getId()){
                return account;
            }
        }
        throw new NonExistingBankAccountException(String.format("Bank account %s does not exist.%n",accountID));
    }
}
