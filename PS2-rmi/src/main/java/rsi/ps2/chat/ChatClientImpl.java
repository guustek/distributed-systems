package rsi.ps2.chat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import rsi.ps2.RmiClient;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    
    private final String name;
    private final ChatServer server;
    
    protected ChatClientImpl(String name, ChatServer server) throws RemoteException {
        super();
        this.name = name;
        this.server = server;
    }
    
    public void sendToServer(String message) throws RemoteException {
        this.server.send(new Message(name, message));
    }
    
    @Override
    public void receive(Message message) {
        System.out.println(message);
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String name = args[0];
        String host = args.length > 1 ? args[1] : "localhost";
        RmiClient rmiClient = new RmiClient(host);
        
        ChatServer remoteObject = rmiClient.getRemoteObject(ChatServer.REMOTE_NAME);
        ChatClient client = new ChatClientImpl(name, remoteObject);
        remoteObject.addClient(client);
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println();
            
            String message = scanner.nextLine();
            client.sendToServer(message);
            
        }
    }
}
