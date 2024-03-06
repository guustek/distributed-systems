package rsi.ps2.chat.server;

import java.rmi.ConnectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import rsi.ps2.RmiServer;
import rsi.ps2.chat.client.ChatClient;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    
    private final Map<String, ChatClient> clients = new HashMap<>();

    private static final String SERVER_NAME = "Server";
    protected ChatServerImpl() throws RemoteException {
        super();
    }

    @Override
    public void connect(ChatClient client) throws RemoteException {
        String clientName = client.getName();
        clients.putIfAbsent(clientName, client);
        String message = clientName + " joined the server. Connected clients: " + clients.keySet();
        System.out.println(message);
        send(new Message(SERVER_NAME, message));
    }

    @Override
    public void disconnect(ChatClient client) throws RemoteException {
        String clientName = client.getName();
        disconnect(clientName);

    }

    private void disconnect(String clientName) {
        clients.remove(clientName);
        String message = clientName + " disconnected. Connected clients: " + clients.keySet();
        System.out.println(message);
        try {
            send(new Message(SERVER_NAME, message));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void send(Message message) throws RemoteException {
        Set<String> unreachableClients = new HashSet<>();

        for (Map.Entry<String, ChatClient> entry : clients.entrySet()) {
            String clientName = entry.getKey();
            ChatClient client = entry.getValue();
            try {
                client.receive(message);
            } catch (ConnectException ex) {
                unreachableClients.add(clientName);
            }
        }

      unreachableClients.forEach(this::disconnect);
    }
}
