package rsi.ps2.tictactoe.server;

public enum MarkType {

    X('x'),
    O('o');

    private final char markCharacter;

    MarkType(char markCharacter) {
        this.markCharacter = markCharacter;
    }

    public char getMarkCharacter() {
        return markCharacter;
    }

    @Override
    public String toString() {
        return String.valueOf(markCharacter);
    }
}
