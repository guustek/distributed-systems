package org.example;

import jakarta.annotation.Resource;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/send")
public class JMSProducer extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(JMSProducer.class.getName());
    
    @Resource(lookup = "jms/MyQueueFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/MyQueue")
    private Queue queue;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("message");
        if (text == null || text.isEmpty()) {
            text = "Default Message";
        }
        
        logger.info("Attempting to send message: " + text);
        
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(queue)) {
            
            TextMessage message = session.createTextMessage(text);
            producer.send(message);
            logger.info("Message sent successfully: " + text);
            response.getWriter().write("Message sent: " + text);
            
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "Error sending JMS message", e);
            throw new ServletException("Error sending JMS message", e);
        }
    }
}
