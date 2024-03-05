package rsi.ps2.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    
    void sendToServer(String message) throws RemoteException;
    
    void receive(Message message);
}
