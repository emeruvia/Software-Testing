
import account.BankAccount;
import exceptions.AccountOverdrawnException;
import exceptions.InsufficientFundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountTest {

  private BankAccount account1;
  private BankAccount account2;

  @BeforeEach
  public void setUp() throws InsufficientFundsException {
    account1 = new BankAccount();
    account2 = new BankAccount(1000);
  }

  @AfterEach
  public void tearDown() {
    account1 = null;
    account2 = null;
  }

  @Test
  public void account1_shouldCreateAccountWithDefaultBalance() {
    BankAccount defaultAccount = new BankAccount();
    assertEquals(account1.getBalance(), defaultAccount.getBalance(), 0);
  }

  @Test
  public void account2_shouldCreateAccountWithCustomBalance() throws InsufficientFundsException {
    BankAccount customAccount = new BankAccount(1000);
    assertEquals(account2.getBalance(), customAccount.getBalance(), 0);
  }

  @Test
  public void accounts_shouldGetBalance() {
    assertEquals(account1.getBalance(), 75, 0);
    assertEquals(account2.getBalance(), 1000, 0);
  }

  @Test
  public void account1_shouldNotBeAPremiumAccount() {
    assertFalse(account1.isPremiumAccount());
  }

  @Test
  public void account2_shouldBeAPremiumAccount() {
    assertTrue(account2.isPremiumAccount());
  }

  @Test
  public void account1_shouldNotTransfer_accountOverdrawnException()
      throws AccountOverdrawnException, InsufficientFundsException {
    BankAccount account = new BankAccount();
    assertThrows(AccountOverdrawnException.class, () -> account1.transfer(100, account));
  }

  @Test
  public void account1_shouldNotTransfer_insufficientFundsException()
      throws AccountOverdrawnException, InsufficientFundsException {
    BankAccount account = new BankAccount();
    assertThrows(InsufficientFundsException.class, () -> account1.transfer(10, account));
  }

  @Test
  public void account2_shouldNotTransfer_accountOverdrawnException()
      throws AccountOverdrawnException, InsufficientFundsException {
    BankAccount account = new BankAccount();
    assertThrows(AccountOverdrawnException.class, () -> account2.transfer(1100, account));
  }

  @Test
  public void account2_shouldNotTransfer_insufficientFundsException()
      throws AccountOverdrawnException, InsufficientFundsException {
    BankAccount account = new BankAccount();
    assertThrows(InsufficientFundsException.class, () -> account2.transfer(950, account));
  }

  @Test
  public void account2_shouldTransferWithoutExceptions()
      throws InsufficientFundsException, AccountOverdrawnException {
    BankAccount account = new BankAccount(100);
    account2.transfer(500, account);
    assertEquals(account2.getBalance(), 500, 0);
    assertEquals(account.getBalance(), 600, 0);
  }

  @Test
  public void account2_shouldWithdraw()
      throws AccountOverdrawnException, InsufficientFundsException {
    account2.withdraw(500);
    assertEquals(500, account2.getBalance(), 0);
  }

  @Test
  public void account1_shouldNotWithdraw_insufficientFundsException()
      throws InsufficientFundsException, AccountOverdrawnException {
    assertThrows(InsufficientFundsException.class, () -> account1.withdraw(10));
  }

  @Test
  public void account1_shouldNotWithdraw_accountOverdrawnException()
      throws AccountOverdrawnException, InsufficientFundsException {
    assertThrows(AccountOverdrawnException.class, () -> account1.withdraw(100));
  }

  @Test
  public void account1_shouldDepositAmount_assertsBalance()
      throws InsufficientFundsException, AccountOverdrawnException {
    account1.deposit(100);
    double balance = account1.getBalance();
    assertEquals(175, balance, 0);
  }

  @Test
  public void account1_shouldNotDepositAmount_accountOverdrawnException()
      throws InsufficientFundsException, AccountOverdrawnException {
    assertThrows(AccountOverdrawnException.class, () -> account1.deposit(-100));
  }

  @Test
  public void account2_shouldNotDepositAmount_insufficientFundsException()
      throws InsufficientFundsException, AccountOverdrawnException {
    assertThrows(InsufficientFundsException.class, () -> account2.deposit(-950));
  }
}