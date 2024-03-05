package rsi.ps2.chat;

import java.io.Serializable;
import java.util.Objects;

public final class Message implements Serializable {
    private final String author;
    private final String message;
    
    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }
    
    public String author() {
        return author;
    }
    
    public String message() {
        return message;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Message that = (Message) obj;
        return Objects.equals(this.author, that.author) &&
                Objects.equals(this.message, that.message);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(author, message);
    }
    
    @Override
    public String toString() {
        return "[" + author + "]: " + message;
    }
    
}
