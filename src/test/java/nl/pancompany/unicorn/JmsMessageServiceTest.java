package nl.pancompany.unicorn;

import jakarta.jms.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class JmsMessageServiceTest {

    JmsMessageService jmsMessageService;
    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    MessageProducer messageProducer;
    Queue queue;
    TextMessage textMessage;

    @BeforeEach
    public void setUp() {
        connectionFactory = mock(ConnectionFactory.class);
        jmsMessageService = new JmsMessageService(connectionFactory);
        connection = mock(Connection.class);
        session = mock(Session.class);
        messageProducer = mock(MessageProducer.class);
        queue = mock(Queue.class);
        textMessage = mock(TextMessage.class);
    }

    @Test
    public void sendsMessage() throws JMSException {
        var destination = "destination";
        var message = "message";
        var expectedMessage = "message\nWe take no responsibilities";
        when(connectionFactory.createConnection()).thenReturn(connection);
        when(connection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(session);
        when(session.createQueue(destination)).thenReturn(queue);
        when(session.createProducer(queue)).thenReturn(messageProducer);

        when(session.createTextMessage(expectedMessage)).thenReturn(textMessage);

        jmsMessageService.sendMessage(destination, message);

        verify(messageProducer).send(textMessage);
    }
}
