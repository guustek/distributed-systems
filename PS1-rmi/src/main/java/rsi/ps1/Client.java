package rsi.ps1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws NotBoundException, RemoteException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = Registry.REGISTRY_PORT;

        //get remote registry
        String remoteRegistryUrl = "rmi://" + host + ":" + port;
        System.out.println("Looking for remote registry at: " + remoteRegistryUrl);
        Registry remoteRegistry = LocateRegistry.getRegistry(host, port);

        //get remote object
        String remoteObjectUrl = remoteRegistryUrl + "/" + Calculator.REMOTE_NAME;
        System.out.println("Looking for remote object at: " + remoteObjectUrl);
        Calculator remoteObject = (Calculator) remoteRegistry.lookup(Calculator.REMOTE_NAME);
        System.out.println("Found remote object");

        Scanner scanner = new Scanner(System.in);
        double firstNumber;
        String operation;
        double secondNumber;

        while (true) {
            System.out.println();
            System.out.print("Enter math operation: ");

            try {
                firstNumber = scanner.nextDouble();
                operation = scanner.next();
                secondNumber = scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input!");
                scanner.next();
                continue;
            }

            try {
                Number result;
                switch (operation) {
                    case "+":
                        result = remoteObject.add(firstNumber, secondNumber);
                        break;
                    case "-":
                        result = remoteObject.subtract(firstNumber, secondNumber);
                        break;
                    case "*":
                        result = remoteObject.multiply(firstNumber, secondNumber);
                        break;
                    case "/":
                        result = remoteObject.divide(firstNumber, secondNumber);
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported operation: " + operation);
                }
                System.out.println("Result: " + result);
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}