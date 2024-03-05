package rsi.ps2.products;

import java.io.Serializable;
import java.util.Objects;

final class Product implements Serializable {
    
    private final String name;
    private final String description;
    
    Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String name() {
        return name;
    }
    
    public String description() {
        return description;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
    
    @Override
    public String toString() {
        return "Product[" +
                "name=" + name + ", " +
                "description=" + description + ']';
    }
    
}
