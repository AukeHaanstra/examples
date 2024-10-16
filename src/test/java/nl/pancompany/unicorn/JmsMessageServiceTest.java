package nl.pancompany.unicorn;

import jakarta.jms.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class JmsMessageServiceTest {

    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    MessageProducer messageProducer;
    Queue queue;
    TextMessage textMessage;

    @BeforeEach
    public void setUp() {
        connectionFactory = Mockito.mock(ConnectionFactory.class);
        connection = mock(Connection.class);
        session = mock(Session.class);
        messageProducer = mock(MessageProducer.class);
        queue = mock(Queue.class);
        textMessage = mock(TextMessage.class);
    }

    @Test
    public void test() throws JMSException {
        String destination = "destination";
        String message = "message";
        when(connectionFactory.createConnection()).thenReturn(connection);
        when(connection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(session);
        when(session.createQueue(destination)).thenReturn(queue);
        when(session.createProducer(queue)).thenReturn(messageProducer);
        when(session.createTextMessage(message)).thenReturn(textMessage);

        new JmsMessageService(connectionFactory).sendMessage("destination", "message");

        Mockito.verify(messageProducer).send(textMessage);
    }
}
