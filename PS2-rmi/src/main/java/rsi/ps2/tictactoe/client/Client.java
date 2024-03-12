package rsi.ps2.tictactoe.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import rsi.ps2.RmiClient;
import rsi.ps2.tictactoe.server.MarkType;
import rsi.ps2.tictactoe.server.TicTacToeServer;

public class Client {
    public static void main(String[] args) throws NotBoundException, RemoteException {
        String host = args.length > 0 ? args[0] : "localhost";
        RmiClient rmiClient = new RmiClient(host);

        TicTacToeServer remoteServer = rmiClient.getRemoteObject(TicTacToeServer.REMOTE_NAME);

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();

        TicTacToeClient client = new TicTacToeClientImpl(remoteServer);
        MarkType markType = remoteServer.connect(client);
        if(markType == null) {
            System.exit(0);
        }

        while(!remoteServer.isGameOn()) {

        }


        while (remoteServer.isGameOn()) {
            if (remoteServer.getCurrentMarkTurn() == markType) {
                System.out.print("Type coordinates (x, y): ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                boolean put = remoteServer.putMark(x, y);
                if(!put) {
                    System.out.println("Incorrect coordinate");
                }
            }
        }
    }
}
