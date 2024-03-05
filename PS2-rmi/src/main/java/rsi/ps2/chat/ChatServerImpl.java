package rsi.ps2.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.LinkedList;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    
    private final Collection<ChatClient> clients = new LinkedList<>();
    protected ChatServerImpl() throws RemoteException {
        super();
    }
    
    @Override
    public void addClient(ChatClient client) throws RemoteException {
        clients.add(client);
    }
    
    @Override
    public void send(Message message) throws RemoteException {
        clients.forEach(client -> client.receive(message));
    }
}
