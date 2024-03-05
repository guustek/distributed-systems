package rsi.ps2.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {

    String REMOTE_NAME = "chatServer";
    
    void addClient(ChatClient client) throws RemoteException;
    
    void send(Message message) throws RemoteException;
}
