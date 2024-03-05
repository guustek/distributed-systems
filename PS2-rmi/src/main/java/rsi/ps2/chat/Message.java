package rsi.ps2.chat;

import java.io.Serializable;

public record Message(String author, String message) implements Serializable {
}
