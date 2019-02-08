package account;

import exceptions.AccountOverdrawnException;
import exceptions.InsufficientFundsException;

public class BankAccount {

  // Constants
  private final static double requiredMinimum = 75.0;
  private final static double premiumAmount = 800.0;

  // Attributes
  private double balance;

  // Constructs a bank account with fifty dollar balance
  public BankAccount() {
    balance = BankAccount.requiredMinimum;
  }

  // Constructs a bank account with a given balance
  // Throws exception if the initial balance is less than the required minimum
  public BankAccount(double initialBalance) {
    balance = initialBalance;
  }

  // Deposits money into the bank account
  // Considering the fact that the amount could be a negative number, throw the
  // respective exception.
  public void deposit(double amount) throws AccountOverdrawnException, InsufficientFundsException {
    double newBalance = balance + amount;
    if (newBalance < 0) {
      throw new AccountOverdrawnException();
    } else if (newBalance < requiredMinimum) {
      throw new InsufficientFundsException();
    }
    balance = newBalance;
  }

  // Withdraws money from the bank account
  // Throws an exception if the account is overdrawn, less than 0
  public void withdraw(double amount) throws
      AccountOverdrawnException, InsufficientFundsException {
    double newBalance = balance - amount;
    if (newBalance < 0) {
      throw new AccountOverdrawnException();
    } else if (newBalance < requiredMinimum) {
      throw new InsufficientFundsException();
    }
    balance = newBalance;
  }

  // Transfers money
  //Throws an exception if the money withdrawn is less than 0
  // Throws an exception if the balance of the account is less than the required minimum
  public void transfer(double amount, BankAccount toAccount)
      throws AccountOverdrawnException, InsufficientFundsException {
    this.withdraw(amount);
    if (getBalance() < requiredMinimum) {
      throw new InsufficientFundsException();
    }
    toAccount.deposit(amount);
  }

  // Determines whether or not an account has premium status
  public boolean isPremiumAccount() {
    return (balance > BankAccount.premiumAmount);
  }

  // Gets the current balance of the bank account
  public double getBalance() {
    return balance;
  }
}
