// File: ProductComparison.java
class Product {
    int productId;
    String productName;

    public Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return productId == other.productId;
    }
}

public class ProductComparison {
    public static void main(String[] args) {
        Product p1 = new Product(1, "Laptop");
        Product p2 = new Product(1, "Laptop");
        Product p3 = p1;

        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        System.out.println("p1 == p3 : " + (p1 == p3));
    }
}
