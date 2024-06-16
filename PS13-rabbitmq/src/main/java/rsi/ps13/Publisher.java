package rsi.ps13;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
public class Publisher {

    public static final String QUEUE_NAME = "PS13";

    public static void main(String[] argv) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Scanner in = new Scanner(System.in);

            while (true) {
                String message = in.nextLine();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
