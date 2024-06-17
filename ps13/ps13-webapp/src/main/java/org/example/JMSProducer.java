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
