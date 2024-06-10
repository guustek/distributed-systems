package org.example;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/MyQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class JMSConsumer implements MessageListener {
    
    private static final Logger logger = Logger.getLogger(JMSConsumer.class.getName());
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                logger.info("Received message: " + text);
            } catch (JMSException e) {
                logger.log(Level.SEVERE, "Error processing JMS message", e);
            }
        } else {
            logger.warning("Received non-text message");
        }
    }
}
