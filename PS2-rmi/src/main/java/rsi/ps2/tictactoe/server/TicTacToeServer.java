package rsi.ps2.tictactoe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.tictactoe.client.TicTacToeClient;

public interface TicTacToeServer extends Remote {
    String REMOTE_NAME = "TicTacToe";

    MarkType connect(TicTacToeClient client) throws RemoteException;

    boolean putMark(int x, int y) throws RemoteException;

    boolean isGameOn() throws RemoteException;

    MarkType getCurrentMarkTurn() throws RemoteException;
}
