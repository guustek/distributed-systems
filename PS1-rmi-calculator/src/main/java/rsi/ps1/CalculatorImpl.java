package rsi.ps1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    protected CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public Number add(Number first, Number second) throws RemoteException {
        double firstDouble = first.doubleValue();
        double secondDouble = second.doubleValue();
        Number result = firstDouble + secondDouble;
        System.out.println("Server calculating: " + first + " + " + second + " = " + result);
        return result;
    }

    @Override
    public Number subtract(Number first, Number second) throws RemoteException {
        double firstDouble = first.doubleValue();
        double secondDouble = second.doubleValue();
        Number result = firstDouble - secondDouble;
        System.out.println("Server calculating: " + first + " - " + second + " = " + result);
        return result;
    }

    @Override
    public Number multiply(Number first, Number second) throws RemoteException {
        double firstDouble = first.doubleValue();
        double secondDouble = second.doubleValue();
        Number result = firstDouble * secondDouble;
        System.out.println("Server calculating: " + first + " * " + second + " = " + result);
        return result;
    }

    @Override
    public Number divide(Number first, Number second) throws RemoteException {
        double firstDouble = first.doubleValue();
        double secondDouble = second.doubleValue();
        Number result = firstDouble / secondDouble;
        System.out.println("Server calculating: " + first + " / " + second + " = " + result);
        return result;
    }
}
