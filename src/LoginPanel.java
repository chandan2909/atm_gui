//Displays login screen and authenticates users.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField pinField;
    private JLabel statusLabel;
    private ATMDatabase db;
    private ATM atm;

    public LoginPanel(ATMDatabase db, ATM atm) {
        this.db = db;
        this.atm = atm;

        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("PIN:"));
        pinField = new JPasswordField();
        add(pinField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        add(loginButton);

        statusLabel = new JLabel("");
        add(statusLabel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String pin = new String(pinField.getPassword());
            int userId = db.validateUser(username, pin);
            if (userId != -1) {
                statusLabel.setText("Login successful!");
                atm.showMainPanel(userId);
            } else {
                statusLabel.setText("Invalid username or PIN.");
            }
        }
    }
}
