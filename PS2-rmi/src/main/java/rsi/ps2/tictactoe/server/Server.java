package rsi.ps2.tictactoe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.RmiServer;

public class Server {
    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServer(false);
        Remote object = new TicTacToeServerImpl();
        rmiServer.registerObject(object, TicTacToeServer.REMOTE_NAME);
        System.out.println();
        System.out.println();
        System.out.println("Server started.");
    }
}
