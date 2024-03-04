package rsi.ps1.rmi.calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    String OBJECT_NAME = "calculator";

    Number add(Number first, Number second) throws RemoteException;

    Number subtract(Number first, Number second) throws RemoteException;

    Number multiply(Number first, Number second) throws RemoteException;

    Number divide(Number first, Number second) throws RemoteException;
}
