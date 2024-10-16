package nl.pancompany.unicorn.wrapped;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageService {

    interface MessageSender {
        void sendMessage(String destination, String message) throws SendingFailedException;

        class SendingFailedException extends Exception {
            public SendingFailedException(Throwable cause) {
                super(cause);
            }
        }
    }

    private final MessageSender messageSender;

    public void sendMessage(String destination, String message) {
        try {
            messageSender.sendMessage(destination, addFooter(message));
        } catch (MessageSender.SendingFailedException e) {
            // Some logic to add the letter to an unsent letter queue
            throw new RuntimeException(e);
        }
    }

    private String addFooter(String message) {
        return message + "\nWe take no responsibilities";
    }


}