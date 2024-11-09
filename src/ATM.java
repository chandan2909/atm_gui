//Main window, manages showing and hiding panels.
import javax.swing.*;
import java.awt.*;

public class ATM extends JFrame {
    private ATMDatabase db;
    private LoginPanel loginPanel;
    private MainPanel mainPanel;

    public ATM() {
        setTitle("ATM Application");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize database connection
        db = new ATMDatabase();

        // Setup login panel
        loginPanel = new LoginPanel(db, this);
        add(loginPanel);

        setVisible(true);
    }

    public void showMainPanel(int userId) {
        remove(loginPanel);
        mainPanel = new MainPanel(db, userId, this);
        add(mainPanel);
        revalidate();
        repaint();
    }

    public void showLoginPanel() {
        remove(mainPanel);
        add(loginPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new ATM();
    }
}
