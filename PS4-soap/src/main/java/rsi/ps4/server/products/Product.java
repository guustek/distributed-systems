package rsi.ps4.server.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Product")
@XmlType(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    private String name;
    private String description;
    private int price;

    public Product() {
    }

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', description='%s', price=%d}", name, description, price);
    }
}
