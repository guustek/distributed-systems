package rsi.ps2.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImpl extends UnicastRemoteObject implements Chat {
    protected ChatImpl() throws RemoteException {
        super();
    }
}
