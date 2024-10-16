package nl.pancompany.unicorn.wrapped;

import nl.pancompany.unicorn.wrapped.MessageService.MessageSender.SendingFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class MessageSenderTest {

    MessageService messageService;
    MessageService.MessageSender messageSender;

    @BeforeEach
    public void setUp() {
        messageSender = mock(MessageService.MessageSender.class);
        messageService = new MessageService(messageSender);
    }

    @Test
    public void sendsMessage() throws SendingFailedException {
        String destination = "destination";
        String message = "message";
        String expectedMessage = "message\nWe take no responsibilities";

        messageService.sendMessage(destination, message);

        Mockito.verify(messageSender).sendMessage(destination, expectedMessage);
    }


}
