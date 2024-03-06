package rsi.ps2.chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.RmiServer;

public class Server {
    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServer();
        Remote object = new ChatServerImpl();
        rmiServer.registerObject(object, ChatServer.REMOTE_NAME);
        System.out.println();
        System.out.println();
        System.out.println("Server started.");

        while (true) {
        }
    }
}
