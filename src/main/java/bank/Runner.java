package bank;

import bank.cli.BankCommandLineManager;

public class Runner {
    public static void main(String[] args) {
        BankCommandLineManager manager = new BankCommandLineManager();
        manager.start();
    }
}
