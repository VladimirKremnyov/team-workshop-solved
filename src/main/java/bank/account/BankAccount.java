package bank.account;

import bank.Person;
import bank.exceptions.InsufficientFundsException;

import java.util.*;

public class BankAccount {
    private static int counter = 0;
    private int id;
    private Person owner;
    private double balance;
    private double interest;
    private InterestType interestType;
    private Queue<String> operations;

    public BankAccount(Person owner, double interest, InterestType interestType){
        this.owner = owner;
        this.interest = interest;
        this.interestType = interestType;
        this.id = counter++;
        this.operations = new ArrayDeque<>(5);
    }

    public void add(double amount){
        balance += amount;
        addOperation(String.format("%s %.2f%n", "added", amount));
    }
    public boolean withdraw(double amount) throws InsufficientFundsException {
        if(interest > 1){
            return false;
        }
        if(amount > balance){
            throw new InsufficientFundsException("Not enough money to complete operation");
        }

        balance -= amount;
        addOperation(String.format("%s %.2f%n", "withdrawn", amount));
        return true;
    }

    public List<String> getHistory(){
        List<String> operationsList = new ArrayList<>(this.operations);
        addOperation(String.format("%s%n", "viewed history"));
        return operationsList;
    }

    public boolean transfer(BankAccount account, double amount) throws InsufficientFundsException {
        boolean withdrawn = withdraw(amount);
        if(withdrawn){
            account.add(amount);
            addOperation(String.format("%s %.2f to %s %n","transferred",amount,account.getId()));
        }
        return withdrawn;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public double getInterest() {
        return interest;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    private void addOperation(String operation){
        if(operations.size() == 5){
            operations.remove();
        }
        operations.add(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
