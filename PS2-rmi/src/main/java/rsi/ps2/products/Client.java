package rsi.ps2.products;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import rsi.ps2.RmiClient;

class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String host = args.length > 0 ? args[0] : "localhost";
        RmiClient rmiClient = new RmiClient(host);
        ProductRepository remoteObject = rmiClient.getRemoteObject(ProductRepository.REMOTE_NAME);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1 - Get all products");
            System.out.println("2 - Get product by name");
            System.out.println();
            System.out.print("Select operation: ");

            int selection = scanner.nextInt();
            switch (selection) {
                case 1 -> System.out.println(remoteObject.getAll());
                case 2 -> {
                    System.out.print("Name: ");
                    String name = scanner.next();
                    Product result = remoteObject.getByName(name);
                    System.out.println(result != null ? result : "Product with name" + name + " not found");
                }
            }
        }
    }
}
