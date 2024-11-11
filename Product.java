class Product {
    int id;
    String name;
    double price;
    int quantity;
    String category; // New category field

    // Updated constructor to include category
    public Product(int id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Price
    public double getPrice() {
        return price;
    }

    // Method to format product information for file writing (optional)
    public String toFileFormat() {
        return id + "," + name + "," + price + "," + quantity + "," + category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity + ", Category: " + category;
    }
}
