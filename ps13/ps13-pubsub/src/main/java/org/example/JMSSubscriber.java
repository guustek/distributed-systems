package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(mappedName = "jms/MyTopic", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic")
})
public class JMSSubscriber implements MessageListener {
    
    private static final Logger logger = Logger.getLogger(JMSSubscriber.class.getName());
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Received message: " + text);
                logger.info("Received message: " + text);
            } catch (JMSException e) {
                System.err.println("Error processing JMS message");
                logger.log(Level.SEVERE, "Error processing JMS message", e);
            }
        } else {
            System.out.println("Received non-text message");
            logger.warning("Received non-text message");
        }
    }
}

