Welcome to OOD workshop, guys! Today we are going to create a Bank. So lets start and here is your task: 

**Bank account**
 
1) It should have unique number and store information about its owner - First, Last names and age.

2) It should store information about the balance of the account, the interest of the account and the type of the interest (complex and simple year interest).

3) If someone tries to create a bank account with invalid information an appropriate exception should be thrown.

4) It should support add, withdraw, transfer and show history operations.

5) When the interest is greater than 1% the withdraws are forbidden.

6) It should remember the last 5 operations of the account.

**User interface**

Create a Command Line Interface for operating with the Bank accounts.
It should have the following functions:

1) _create_bank_account_ It should ask the user for the needed information and if everything is ok it should create a new bank account.

2) _show_history_ It should ask the user for the bank account number and show its history.

3) _add_money_ It should ask the user for the amount and the bank account number.

4) _withdraw_money_ It should ask the user for the amount and the bank account number.

5) _transfer_money_ It should ask the user for the origin and destination bank account numbers and the amount

6) _calculate_amount_ It should ask the user for bank account number and number of months. It should return the amount after the given number of months.
    
**Formulas, that you will need to calculate amount:**
    
    Simple Interest = P×r×n
    where:
    P = Principal Amount
    r = Annual Interest Rate
    n = Term of Loan, in years

    ​	  
    Compound Interest = P×((1+r)^t)−P
    where:
    P = Principal Amount
    r = Annual Interest Rate
    t = Number of years interest is applied
    ​	
    
Notes: you should create and use your own custom exceptions:

_InsufficientFundsException_

_NonExistingBankAccountException_
 
Here we go, guys, good luck and have fun!