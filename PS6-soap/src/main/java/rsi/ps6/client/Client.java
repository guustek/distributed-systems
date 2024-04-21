package rsi.ps6.client;

import javax.swing.*;
import javax.xml.ws.soap.MTOMFeature;

import rsi.ps6.client.generated.imageservice.ImageService;
import rsi.ps6.client.generated.imageservice.ImageServiceImpl;

public class Client {

    public static void main(String[] args) {
        ImageServiceImpl client = new ImageServiceImpl();
        ImageService port = client.getImageServiceImplPort(new MTOMFeature());

        byte[] bytes = port.downloadImage("logo_pb.png");

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(bytes));
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
