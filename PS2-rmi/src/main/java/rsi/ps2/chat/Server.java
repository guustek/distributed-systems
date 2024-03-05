package rsi.ps2.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.RmiServer;

public class Server {
    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServer();
        Remote object = new ChatImpl();
        rmiServer.registerObject(object, ChatImpl.REMOTE_NAME);

        while(true) {}
    }
}
