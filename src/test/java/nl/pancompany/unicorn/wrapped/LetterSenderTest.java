package nl.pancompany.unicorn.wrapped;

import nl.pancompany.unicorn.wrapped.LetterSender.MessageSender.SendingFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class LetterSenderTest {

    LetterSender letterSender;
    LetterSender.MessageSender messageSender;

    @BeforeEach
    public void setUp() {
        messageSender = mock(LetterSender.MessageSender.class);
        letterSender = new LetterSender(messageSender);
    }

    @Test
    public void sendsMessage() throws SendingFailedException {
        String destination = "destination";
        String message = "message";
        String expectedMessage = "message\nWe take no responsibilities";

        letterSender.sendMessage(destination, message);

        Mockito.verify(messageSender).sendMessage(destination, expectedMessage);
    }


}
