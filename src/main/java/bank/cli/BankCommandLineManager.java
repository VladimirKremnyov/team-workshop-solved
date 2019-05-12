package bank.cli;

import bank.Bank;
import bank.Person;
import bank.account.InterestType;
import bank.exceptions.InsufficientFundsException;
import bank.exceptions.NonExistingBankAccountException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankCommandLineManager {

    private Bank bank;

    private static final String SUCCESS = "Operation completed.";
    private static final String PROMPT_BANK_ACCOUNT = "Enter bank account ID: ";
    private static final String FAIL = "Operation failed.";
    private static final String CANNOT_WITHDRAW = "Either you do not have enough money to complete the operation or " +
            "your interest is greater than 1% in which case withdraws are forbidden.";
    private static final String PROMPT_AMOUNT = "Enter amount: ";
    private static final String OPTIONS = "Choose an option: \n" +
            "1: Create a bank account.\n" +
            "2: Show history.\n" +
            "3: Deposit money.\n" +
            "4: Withdraw money\n" +
            "5: Transfer money.\n" +
            "6: Calculate amount\n" +
            "7: Exit";


    public BankCommandLineManager() {
        bank = new Bank();
    }

    private void showOptions() {
        System.out.println(OPTIONS);
    }

    public void start() {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            showOptions();
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option > 6) {
                    break;
                }

                switch (option) {
                    case 1:
                        createBankAccount(scanner);
                        break;
                    case 2:
                        showHistory(scanner);
                        break;
                    case 3:
                        addMoney(scanner);
                        break;
                    case 4:
                        withdrawMoney(scanner);
                        break;
                    case 5:
                        transferMoney(scanner);
                        break;
                    case 6:
                        calculateAmount(scanner);
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid argument. Try again.");
                scanner.next();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
        scanner.close();
    }

    private Person readOwner(Scanner scanner) {
        System.out.println("Enter owner's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter owner's lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Enter owner's age: ");
        int age = scanner.nextInt();
        return new Person(name, lastname, age);
    }

    private InterestType readInterestType(Scanner scanner) {
        System.out.println("Choose interest type:\n1. Simple\n2. Complex");
        short option = scanner.nextShort();

        switch (option) {
            case 1:
                return InterestType.SIMPLE;
            case 2:
                return InterestType.COMPLEX;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void createBankAccount(Scanner scanner) {
        Person owner = readOwner(scanner);

        System.out.println("Enter interest rate: ");
        double interest = scanner.nextDouble();

        InterestType interestType = readInterestType(scanner);
        bank.createBankAccount(owner, interest, interestType);
    }

    private void showHistory(Scanner scanner) throws NonExistingBankAccountException {
        System.out.println(PROMPT_BANK_ACCOUNT);
        int accountID = scanner.nextInt();

        List<String> operations = bank.showOperations(accountID);
        if (operations.isEmpty()) {
            System.out.println("No operations found for this account");
        } else {
            for (String operation : operations) {
                System.out.println(operation);
            }
        }
    }

    private void addMoney(Scanner scanner) throws NonExistingBankAccountException {
        System.out.println(PROMPT_BANK_ACCOUNT);
        int accountID = scanner.nextInt();

        System.out.println(PROMPT_AMOUNT);
        double amount = scanner.nextDouble();
        bank.addMoney(accountID, amount);
        System.out.println(SUCCESS);
    }

    private void withdrawMoney(Scanner scanner) throws InsufficientFundsException, NonExistingBankAccountException {
        System.out.println(PROMPT_BANK_ACCOUNT);
        int accountID = scanner.nextInt();

        System.out.println(PROMPT_AMOUNT);
        double amount = scanner.nextDouble();

        if (bank.withdrawMoney(accountID, amount)) {
            System.out.println(SUCCESS);
        } else {
            System.err.println(FAIL);
            System.err.println(CANNOT_WITHDRAW);
        }
    }

    private void transferMoney(Scanner scanner) throws InsufficientFundsException, NonExistingBankAccountException {
        System.out.println("Enter source bank account ID: ");
        int source = scanner.nextInt();

        System.out.println("Enter destination bank account ID: ");
        int destination = scanner.nextInt();

        System.out.println(PROMPT_AMOUNT);
        double amount = scanner.nextDouble();

        if (bank.transferMoney(source, destination, amount)) {
            System.out.println(SUCCESS);
        } else {
            System.err.println(FAIL);
            System.err.println(CANNOT_WITHDRAW);
        }
    }

    private void calculateAmount(Scanner scanner) throws NonExistingBankAccountException {
        System.out.println(PROMPT_BANK_ACCOUNT);
        int accountID = scanner.nextInt();

        System.out.println("Enter number of months for which you want to calculate the amount: ");
        int months = scanner.nextInt();

        System.out.println(String.format("%.2f", bank.calculateAmount(accountID, months)));
    }
}
