import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set a welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Inventory Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.CENTER);

        // Create a button to go to the inventory management system
        JButton goToInventoryButton = new JButton("Go to Inventory Management");
        goToInventoryButton.addActionListener(e -> {
            EnhancedInventoryManagementSystem inventorySystem = new EnhancedInventoryManagementSystem();
            inventorySystem.setVisible(true);
            this.dispose(); // Close the home page
        });
        add(goToInventoryButton, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(255, 239, 204)); // Light peach color
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
    }
}
