package org.example.model;

import java.util.Objects;

public class Client extends User {
    private double balance;
    public double amount;
    public String email;
    public String password;
    boolean mailcheck = false;
    boolean passwordcheck = false;
    public Client() {
        super();
        this.balance = 0;
    }

    public Client(Long id, String name, String email,String password ,double balance) {
        super(id, name, email, password);
        this.balance = balance;
    }

    // New method: Deposit funds
    public void deposit(double amount) {
        addFunds(amount);
    }

    // New method: Get full name (assuming User class has getName())
    public String getFullName() {
        return getName();
    }
    public double getamount(){
        return amount;
    }
    // New method: Deduct wrapper
    public void deduct(double amount) {
        deductFunds(amount);
    }

    // Existing balance methods
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Modified to private since we have public deposit/deduct now
    public void addFunds(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance = this.balance+amount;
    }

    // Modified to private since we have public deduct now
    public void deductFunds(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.balance < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance = this.balance - amount;
    }
    public void setEmail(String email) {
        mailcheck = false;
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Email must end with '@gmail.com'");
        } else {
            this.email = email;
            mailcheck = true;
        }
    }
    public String getEmail() {

        return email;
    }
    public boolean isEmailValid() {
        return mailcheck;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String Password) {
        passcheck=false;
        if (Password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        } else if (Password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        } else if (!(Password.matches(".*[a-z].*"))) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }
        else if( (!Character.isUpperCase(Password.charAt(0)) || !Password.contains("!"))){
            throw new IllegalArgumentException("Password must begin with uppercase letter and must contain!");
        }
        else {
            this.password = Password;
            passcheck=true;
        }
    }
    public boolean checkPassword() {
        return passcheck;
    }
    // Rest of existing methods remain unchanged
    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", name='" + getFullName() + '\'' + // Updated to use getFullName()
                ", email='" + getEmail() + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(balance, client.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
    }
}