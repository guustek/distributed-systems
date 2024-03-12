package rsi.ps2.tictactoe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.EnumMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rsi.ps2.tictactoe.client.TicTacToeClient;

public class TicTacToeServerImpl extends UnicastRemoteObject implements TicTacToeServer {

    private static final String SERVER_NAME = "Server";

    private final Board board = new Board();
    private final Map<MarkType, TicTacToeClient> players = new EnumMap<>(MarkType.class);
    private boolean isGameOn = false;

    private MarkType currentMarkTurn;

    protected TicTacToeServerImpl() throws RemoteException {
        super();
    }

    @Override
    public MarkType connect(TicTacToeClient client) throws RemoteException {
        for (MarkType markType : MarkType.values()) {
            if (!players.containsKey(markType)) {
                players.put(markType, client);

                client.receiveMessage("[" + SERVER_NAME + "]: Playing as " + markType.toString());

                if (players.size() == MarkType.values().length) {
                    broadcastMessage("[" + SERVER_NAME + "]: Game started");
                    broadcastMessage(board.toString());
                    isGameOn = true;
                    System.out.println("Game started");
                    currentMarkTurn = MarkType.X;
                } else {
                    client.receiveMessage("[" + SERVER_NAME + "]: Waiting for other player");
                }
                return markType;
            }
        }

        client.receiveMessage("[" + SERVER_NAME + "]: Lobby is full!");
        return null;
    }

    public void broadcastMessage(String message) throws RemoteException {
        for (TicTacToeClient player : players.values()) {
            player.receiveMessage(message);
        }
    }

    @Override
    public boolean putMark(int x, int y) throws RemoteException {
        boolean put = board.put(x, y, currentMarkTurn);
        if (!put) {
            return false;
        }
        for (TicTacToeClient player : players.values()) {
            player.receiveMessage(board.toString());
        }
        if (board.isWin(currentMarkTurn)) {
            broadcastMessage("[" + SERVER_NAME + "]: Player " + currentMarkTurn + " has won!");
            finishGame();
        } else if (board.isDraw()) {
            broadcastMessage("[" + SERVER_NAME + "]: Draw!");
            finishGame();
        }
        currentMarkTurn = currentMarkTurn == MarkType.X ? MarkType.O : MarkType.X;
        if (!isGameOn) {
            scheduleShutdown();
        }
        return true;
    }

    private static void scheduleShutdown() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
    }

    private void finishGame() {
        isGameOn = false;
        System.out.println("Game finished");
    }


    @Override
    public boolean isGameOn() throws RemoteException {
        return isGameOn;
    }

    public MarkType getCurrentMarkTurn() throws RemoteException {
        return currentMarkTurn;
    }
}
