package nl.pancompany.unicorn.wrapped;

import jakarta.jms.*;

public class JmsMessageSender implements LetterSender.MessageSender {

    private final ConnectionFactory connectionFactory;

    public JmsMessageSender(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(String destination, String message) throws SendingFailedException {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(destination);
            producer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        } catch (JMSException e) {
            throw new SendingFailedException(e);
        }
        finally {

            if (producer != null) {
                try {
                    if (session != null) session.close();
                    if (connection != null) connection.close();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
