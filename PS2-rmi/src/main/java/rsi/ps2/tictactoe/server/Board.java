package rsi.ps2.tictactoe.server;

public class Board {
    private final char[][] matrix = createMatrix();

    private char[][] createMatrix() {
        char[][] array = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = '-';
            }
        }
        return array;
    }

    boolean put(int x, int y, MarkType markType) {
        if(x <= 2 && x >= 0 && y <= 2 && y >= 0 && matrix[x][y] == '-') {
            matrix[x][y] = markType.getMarkCharacter();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(System.lineSeparator());
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                String row = " " + matrix[x][y] + " ";
                builder.append(row);
                if (y < matrix[x].length - 1) {
                    builder.append("|");
                }
            }
            builder.append(System.lineSeparator());
            if (x < matrix.length - 1) {
                for (int k = 0; k < matrix[x].length; k++) {
                    builder.append("---");
                    if (k < matrix[x].length - 1) {
                        builder.append("|");
                    }
                }
                builder.append(System.lineSeparator());
            }
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    public boolean isWin(MarkType player) {
        // Check rows
        for (int x = 0; x < 3; x++) {
            if (matrix[x][0] == player.getMarkCharacter() && matrix[x][1] == player.getMarkCharacter() && matrix[x][2] == player.getMarkCharacter()) {
                return true;
            }
        }

        // Check columns
        for (int y = 0; y < 3; y++) {
            if (matrix[0][y] == player.getMarkCharacter() && matrix[1][y] == player.getMarkCharacter() && matrix[2][y] == player.getMarkCharacter()) {
                return true;
            }
        }

        // Check diagonals
        return (matrix[0][0] == player.getMarkCharacter() && matrix[1][1] == player.getMarkCharacter() && matrix[2][2] == player.getMarkCharacter()) ||
                (matrix[0][2] == player.getMarkCharacter() && matrix[1][1] == player.getMarkCharacter() && matrix[2][0] == player.getMarkCharacter());
    }

    public boolean isDraw() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (matrix[x][y] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
