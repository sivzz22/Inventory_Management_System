import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Main GUI class for Inventory Management System
public class InventoryManagementSystem extends JFrame {
    InventoryArray inventoryArray = new InventoryArray();  // Using InventoryArray as an example

    // GUI Components
    JLabel idLabel, nameLabel, priceLabel, quantityLabel;
    JTextField idField, nameField, priceField, quantityField;
    JButton addButton, displayButton, resetButton;
    JTextArea displayArea;

    // Constructor to initialize the GUI
    public InventoryManagementSystem() {
        // Set up frame properties
        setTitle("Inventory Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // GridBag constraints for vertical layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Labels and Text Fields for input
        idLabel = new JLabel("Product ID:");
        idField = new JTextField(10);
        idField.setToolTipText("Enter a unique integer ID for the product");

        nameLabel = new JLabel("Product Name:");
        nameField = new JTextField(15);
        nameField.setToolTipText("Enter the product name");

        priceLabel = new JLabel("Price:");
        priceField = new JTextField(10);
        priceField.setToolTipText("Enter the price of the product");

        quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);
        quantityField.setToolTipText("Enter the quantity in stock");

        // Add Button
        addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        addButton.setToolTipText("Click to add the product to inventory");

        // Display Button
        displayButton = new JButton("Display Products");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProducts();
            }
        });
        displayButton.setToolTipText("Click to display all products in inventory");

        // Reset Button
        resetButton = new JButton("Reset Fields");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        resetButton.setToolTipText("Click to clear all input fields");

        // TextArea to display product list
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        displayArea.setToolTipText("Displays inventory information");
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding components to the frame
        gbc.gridx = 0; gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(priceLabel, gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(quantityLabel, gbc);
        gbc.gridx = 1;
        add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(addButton, gbc);
        gbc.gridx = 1;
        add(displayButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        pack();  // Automatically size the components within the frame
    }

    // Method to add a product to the inventory
    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());

            if (name.isEmpty()) {
                displayArea.setText("Product name cannot be empty.\n");
                return;
            }

            Product product = new Product(id, name, price, quantity);
            inventoryArray.addProduct(product);

            displayArea.setText("Product added successfully!\n");
            resetFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Please enter valid input values.\n");
        }
    }

    // Method to display all products in the inventory
    private void displayProducts() {
        StringBuilder displayText = new StringBuilder("Product Inventory:\n");
        if (inventoryArray.productArray.isEmpty()) {
            displayText.append("No products in inventory.\n");
        } else {
            for (Product product : inventoryArray.productArray) {
                displayText.append(product).append("\n");
            }
        }
        displayArea.setText(displayText.toString());
    }

    // Method to reset input fields
    private void resetFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InventoryManagementSystem inventoryGUI = new InventoryManagementSystem();
                inventoryGUI.setVisible(true);
            }
        });
    }
}

// Supporting classes
class Product {
    int id;
    String name;
    double price;
    int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class InventoryArray {
    ArrayList<Product> productArray = new ArrayList<>();

    public void addProduct(Product product) {
        productArray.add(product);
    }
}
