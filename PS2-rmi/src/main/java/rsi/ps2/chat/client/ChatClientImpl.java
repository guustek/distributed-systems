package rsi.ps2.chat.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rsi.ps2.chat.server.ChatServer;
import rsi.ps2.chat.server.Message;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private final String name;
    private final ChatServer server;

    protected ChatClientImpl(String name, ChatServer server) throws RemoteException {
        super();
        this.name = name;
        this.server = server;
    }

    @Override
    public void receive(Message message) throws RemoteException {
        if (!message.getAuthor().equals(name)) {
            System.out.println(message);
        }
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    void disconnect() {
        try {
            System.out.println("Disconnecting.");
            this.server.disconnect(this);
        } catch (RemoteException ignored) {}
    }
}
