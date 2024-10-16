package nl.pancompany.unicorn;

import jakarta.jms.*;

public class JmsMessageService {

    private final ConnectionFactory connectionFactory;

    public JmsMessageService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(String destination, String message) throws JMSException {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(destination);
            producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(addFooter(message));
            producer.send(textMessage);
        } finally {
            if (producer != null) producer.close();
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }

    private String addFooter(String message) {
        return message + "\nWe take no responsibilities";
    }
}