package rsi.ps2.chat.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rsi.ps2.chat.client.ChatClient;

public interface ChatServer extends Remote, Serializable {

    String REMOTE_NAME = "chatServer";
    
    void connect(ChatClient client) throws RemoteException;

    void disconnect(ChatClient client) throws RemoteException;

    void send(Message message) throws RemoteException;
}
