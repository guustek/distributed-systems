package org.example;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/MyTopic", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
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

