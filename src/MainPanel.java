//Displays ATM operations after successful login.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private ATMDatabase db;
    private int userId;
    private ATM atm;

    public MainPanel(ATMDatabase db, int userId, ATM atm) {
        this.db = db;
        this.userId = userId;
        this.atm = atm;

        setLayout(new GridLayout(6, 1));

        JButton viewBalanceButton = new JButton("View Balance");
        viewBalanceButton.addActionListener(e -> viewBalance());
        add(viewBalanceButton);

        JButton depositButton = new JButton("Deposit Money");
        depositButton.addActionListener(e -> depositMoney());
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(e -> withdrawMoney());
        add(withdrawButton);

        JButton transactionButton = new JButton("Transaction History");
        transactionButton.addActionListener(e -> viewTransactionHistory());
        add(transactionButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> atm.showLoginPanel());
        add(logoutButton);
    }

    private void viewBalance() {
        double balance = db.getBalance(userId);
        JOptionPane.showMessageDialog(this, "Your balance is ₹" + balance);
    }

    private void depositMoney() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        double amount = Double.parseDouble(amountStr);
        db.depositMoney(userId, amount);
        JOptionPane.showMessageDialog(this, "₹" + amount + " deposited successfully!");
    }

    private void withdrawMoney() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        double amount = Double.parseDouble(amountStr);
        boolean success = db.withdrawMoney(userId, amount);
        if (success) {
            JOptionPane.showMessageDialog(this, "₹" + amount + " withdrawn successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient balance!");
        }
    }

    private void viewTransactionHistory() {
        String history = db.getTransactionHistory(userId);
        JOptionPane.showMessageDialog(this, history);
    }
}
