package com.example.tdd.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

  private Account account;

  @BeforeAll
  public void setup() {
    account = new Account(10000);
  }

  @BeforeEach
  public void init () {
    
  }

  @Test
  public void testAccount() throws Exception {
  }

  @Test
  public void testGetBalance() throws Exception {
    assertEquals(10000, account.getBalance(), "getBalance() => " + account.getBalance());

    account = new Account(1000);
    assertEquals(1000, account.getBalance(), "getBalance() => " + account.getBalance());

    account = new Account(0);
    assertEquals(0, account.getBalance(), "getBalance() => " + account.getBalance());
  }

  @Test
  public void testDeposit()  {
    account.deposit(1000);
    assertEquals(11000, account.getBalance());
  }

  @Test
  public void testWithdraw() {
    account.withdraw(1000);
    assertEquals(9000, account.getBalance());
  }

}