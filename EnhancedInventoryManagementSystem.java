import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EnhancedInventoryManagementSystem extends JFrame {
    InventoryArray inventoryArray = new InventoryArray();

    JLabel titleLabel, idLabel, nameLabel, priceLabel, quantityLabel, categoryLabel;
    JTextField idField, nameField, priceField, quantityField;
    JComboBox<String> categoryDropdown;
    JButton addButton, displayButton, resetButton, deleteButton, searchButton, sortButton, clearDisplayButton, saveButton, loadButton;
    JTextArea displayArea;

    public EnhancedInventoryManagementSystem() {
        setTitle("Enhanced Inventory Management System");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set background color for the frame
        getContentPane().setBackground(new Color(255, 239, 204)); // Light peach color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        titleLabel = new JLabel("Inventory Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Labels and Text Fields
        idLabel = new JLabel("Product ID:");
        idField = new JTextField(10);

        nameLabel = new JLabel("Product Name:");
        nameField = new JTextField(15);

        priceLabel = new JLabel("Price:");
        priceField = new JTextField(10);

        quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        categoryLabel = new JLabel("Category:");
        String[] categories = {"Select Category", "Electronics", "Groceries", "Clothing", "Stationery"};
        categoryDropdown = new JComboBox<>(categories);

        // Buttons
        addButton = new JButton("Add Product");
        addButton.setBackground(new Color(102, 204, 255)); // Light blue
        addButton.addActionListener(e -> addProduct());

        displayButton = new JButton("Display Products");
        displayButton.setBackground(new Color(102, 204, 255));
        displayButton.addActionListener(e -> displayProducts());

        resetButton = new JButton("Reset Fields");
        resetButton.setBackground(new Color(255, 204, 204)); // Light red
        resetButton.addActionListener(e -> resetFields());

        deleteButton = new JButton("Delete Product");
        deleteButton.setBackground(new Color(255, 204, 204));
        deleteButton.addActionListener(e -> deleteProduct());

        searchButton = new JButton("Search Product");
        searchButton.setBackground(new Color(102, 204, 255));
        searchButton.addActionListener(e -> searchProduct());

        sortButton = new JButton("Sort by Price");
        sortButton.setBackground(new Color(102, 204, 255));
        sortButton.addActionListener(e -> sortProductsByPrice());

        clearDisplayButton = new JButton("Clear Display");
        clearDisplayButton.setBackground(new Color(255, 204, 204));
        clearDisplayButton.addActionListener(e -> displayArea.setText(""));

        saveButton = new JButton("Save to File");
        saveButton.setBackground(new Color(102, 204, 255));
        saveButton.addActionListener(e -> saveToFile());

        loadButton = new JButton("Load from File");
        loadButton.setBackground(new Color(102, 204, 255));
        loadButton.addActionListener(e -> loadFromFile());

        // Display Area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(240, 240, 240)); // Light gray for text area
        JScrollPane scrollPane = new JScrollPane(displayArea);

        gbc.gridwidth = 1;

        // Adding Components to Layout
        gbc.gridx = 0; gbc.gridy = 1;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(priceLabel, gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(quantityLabel, gbc);
        gbc.gridx = 1;
        add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(categoryLabel, gbc);
        gbc.gridx = 1;
        add(categoryDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(addButton, gbc);
        gbc.gridx = 1;
        add(displayButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(deleteButton, gbc);
        gbc.gridx = 1;
        add(searchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(sortButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        add(clearDisplayButton, gbc);
        gbc.gridx = 1;
        add(saveButton, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        add(loadButton, gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        pack();
    }

    private void addProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String category = (String) categoryDropdown.getSelectedItem();

            if (category == null || category.equals("Select Category")) {
                displayArea.setText("Please select a product category.\n");
                return;
            }

            Product product = new Product(id, name, price, quantity, category);
            inventoryArray.addProduct(product);
            displayArea.setText("Product added successfully!\n");
            resetFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Please enter valid input values.\n");
        }
    }

    private void displayProducts() {
        StringBuilder displayText = new StringBuilder("Product Inventory:\n");
        for (Product product : inventoryArray.productArray) {
            displayText.append(product).append("\n");
        }
        displayArea.setText(displayText.toString());
    }

    private void resetFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryDropdown.setSelectedIndex(0);
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            boolean isDeleted = inventoryArray.deleteProductById(id);
            displayArea.setText(isDeleted ? "Product deleted successfully!\n" : "Product ID not found.\n");
            resetFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Please enter a valid Product ID.\n");
        }
    }

    private void searchProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Product product = inventoryArray.findProductById(id);
            displayArea.setText(product != null ? "Product found:\n" + product + "\n" : "Product ID not found.\n");
            resetFields();
        } catch (NumberFormatException e) {
            displayArea.setText("Please enter a valid Product ID.\n");
        }
    }

    private void sortProductsByPrice() {
        inventoryArray.sortByPrice();
        displayProducts();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"))) {
            for (Product product : inventoryArray.productArray) {
                writer.write(product.toFileFormat());
                writer.newLine();
            }
            displayArea.setText("Inventory saved to file.\n");
        } catch (IOException e) {
            displayArea.setText("Error saving to file.\n");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            inventoryArray.productArray.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                double price = Double.parseDouble(fields[2]);
                int quantity = Integer.parseInt(fields[3]);
                String category = fields[4];
                inventoryArray.addProduct(new Product(id, name, price, quantity, category));
            }
            displayArea.setText("Inventory loaded from file.\n");
            displayProducts();
        } catch (IOException e) {
            displayArea.setText("Error loading from file.\n");
        } catch (NumberFormatException e) {
            displayArea.setText("Error parsing product data.\n");
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        EnhancedInventoryManagementSystem app = new EnhancedInventoryManagementSystem();
        app.setVisible(true);
    });
}

}

class InventoryArray {
    ArrayList<Product> productArray = new ArrayList<>();

    public void addProduct(Product product) {
        productArray.add(product);
    }

    public boolean deleteProductById(int id) {
        return productArray.removeIf(product -> product.getId() == id);
    }

    public Product findProductById(int id) {
        for (Product product : productArray) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void sortByPrice() {
        productArray.sort(Comparator.comparingDouble(Product::getPrice));
    }
}

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(int id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Price: %.2f, Quantity: %d, Category: %s", id, name, price, quantity, category);
    }

    public String toFileFormat() {
        return String.format("%d,%s,%.2f,%d,%s", id, name, price, quantity, category);
    }
}
