package org.example;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/publish")
public class JMSPublisher extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(JMSPublisher.class.getName());
    
    @Resource(lookup = "jms/MyTopicFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/MyTopic")
    private Topic topic;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("message");
        if (text == null || text.isEmpty()) {
            text = "Default Message";
        }
        
        System.out.println("Attempting to publish message: " + text);
        logger.info("Attempting to publish message: " + text);
        
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(topic)) {
            
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
            System.out.println("Message published successfully: " + text);
            logger.info("Message published successfully: " + text);
            response.getWriter().write("Message published: " + text);
            
        } catch (JMSException e) {
            System.err.println("Error publishing JMS message");
            logger.log(Level.SEVERE, "Error publishing JMS message", e);
            throw new ServletException("Error publishing JMS message", e);
        }
    }
}