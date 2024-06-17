package org.example;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/MyQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
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
