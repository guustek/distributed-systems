package rsi.ps2.tictactoe.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeClient extends Remote {

    void receiveMessage(String message) throws RemoteException;

    void disconnect() throws RemoteException;
}
