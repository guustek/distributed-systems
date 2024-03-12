package rsi.ps2.tictactoe.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rsi.ps2.tictactoe.server.TicTacToeServer;

public class TicTacToeClientImpl extends UnicastRemoteObject implements TicTacToeClient {

    private final TicTacToeServer server;
    public TicTacToeClientImpl(TicTacToeServer remoteServer) throws RemoteException {
        super();
        this.server = remoteServer;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }
}
