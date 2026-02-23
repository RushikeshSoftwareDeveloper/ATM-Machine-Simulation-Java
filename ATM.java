package com.Medium;

import java.util.Scanner;

//Bank Account Class
class BankAccount {

 private double balance;
 private final int PIN = 1234;   // Default PIN

 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 public boolean validatePin(int enteredPin) {
     return enteredPin == PIN;
 }

 public void checkBalance() {
     System.out.println("💰 Current Balance: ₹" + balance);
 }

 public void deposit(double amount) {
     if (amount <= 0) {
         System.out.println("❌ Invalid deposit amount.");
         return;
     }
     balance += amount;
     System.out.println("✅ Deposit successful!");
 }

 public void withdraw(double amount) {
     if (amount <= 0) {
         System.out.println("❌ Invalid withdrawal amount.");
     } else if (amount > balance) {
         System.out.println("❌ Insufficient balance!");
     } else {
         balance -= amount;
         System.out.println("✅ Withdrawal successful!");
     }
 }
}

//ATM Class
class Atmapp {

 private BankAccount account;
 private Scanner sc;

 public Atmapp(BankAccount account) {
     this.account = account;
     sc = new Scanner(System.in);
 }

 public void start() {

     try {
         System.out.print("Enter your PIN: ");
         int enteredPin = sc.nextInt();

         if (!account.validatePin(enteredPin)) {
             System.out.println("❌ Incorrect PIN. Access Denied.");
             return;
         }

         int choice;

         do {
             showMenu();
             choice = sc.nextInt();

             switch (choice) {
                 case 1:
                     account.checkBalance();
                     break;

                 case 2:
                     System.out.print("Enter deposit amount: ");
                     double depositAmount = sc.nextDouble();
                     account.deposit(depositAmount);
                     break;

                 case 3:
                     System.out.print("Enter withdrawal amount: ");
                     double withdrawAmount = sc.nextDouble();
                     account.withdraw(withdrawAmount);
                     break;

                 case 4:
                     System.out.println("👋 Thank you for using ATM!");
                     break;

                 default:
                     System.out.println("❌ Invalid choice!");
             }

         } while (choice != 4);

     } catch (Exception e) {
         System.out.println("⚠ Invalid input! Please enter numbers only.");
     } finally {
         sc.close();
     }
 }

 private void showMenu() {
     System.out.println("\n===== ATM MENU =====");
     System.out.println("1. Check Balance");
     System.out.println("2. Deposit");
     System.out.println("3. Withdraw");
     System.out.println("4. Exit");
     System.out.print("Choose option: ");
 }
}

//Main Class
public class ATM {

 public static void main(String[] args) {

     BankAccount account = new BankAccount(5000); // Initial Balance
     Atmapp atm = new Atmapp(account);

     atm.start();
 }
}
