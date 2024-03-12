package rsi.ps2.chat.client;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.chat.server.Message;

public interface ChatClient extends Remote, Serializable {

    String getName() throws RemoteException;
    
    void receive(Message message) throws RemoteException;

}
