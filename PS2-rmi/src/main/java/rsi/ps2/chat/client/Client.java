package rsi.ps2.chat.client;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import rsi.ps2.RmiClient;
import rsi.ps2.chat.server.ChatServer;
import rsi.ps2.chat.server.Message;

public class Client {

    private static final String EXIT_MESSAGE = "exit";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        String host = args.length > 0 ? args[0] : "localhost";
        RmiClient rmiClient = new RmiClient(host, true);

        ChatServer remoteServer = rmiClient.getRemoteObject(ChatServer.REMOTE_NAME);

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        ChatClientImpl client = new ChatClientImpl(name, remoteServer);
        remoteServer.connect(client);
        Runtime.getRuntime().addShutdownHook(new Thread(client::disconnect));

        while (true) {
            String message = scanner.nextLine();
            if (EXIT_MESSAGE.equals(message)) {
                System.exit(0);
            }
            try {
                remoteServer.send(new Message(client.getName(), message));
            } catch (ConnectException ex) {
                System.out.println("Server is not reachable, exiting");
                System.exit(0);
            }
        }
    }
}
