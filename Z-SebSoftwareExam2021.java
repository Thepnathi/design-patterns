package exam;

class Person {
    public static final int EMAIL = 1; // Preference for receiving email
    public static final int SMS = 2; // Preference for receiving SMS
    public static final int TWITTER = 3; // Preference for receiving Twitter
    private String messageAddress; // destination where to send the message

    public String getMessageAddress() {
        return(this.messageAddress);
    }

    private int messagingPreference = 0;

    public final int getMessagingPreference() {
        return(messagingPreference);
    }

    public Person(String messageAddress, int messagingPreference) {
        this.messageAddress = messageAddress;
        this.messagingPreference = messagingPreference;
    }
}

abstract class Message {
    protected Person sender;
    protected Person recipient;
    protected String messageContent;

    public Message(Person sender, Person recipient, String messageContent) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageContent = messageContent;
    }

    protected abstract void printMessage();
}


class Email extends Message {
    public Email(Person sender, Person recipient, String messageContent) {
        super(sender, recipient, messageContent);
    }

    @Override
    public final void printMessage() {
        System.out.println("Sending to Email ======== "
        + "\nSender: " + sender.getMessageAddress() 
        + "\nReceiver: " + recipient.getMessageAddress()
        + "\nMessage Body: " + messageContent);
    }
}

class SMS extends Message {
    public SMS(Person sender, Person recipient, String messageContent) {
        super(sender, recipient, messageContent);
    }

    @Override
    public final void printMessage() {
        System.out.println("Sending to SMS ======== "
        + "\nSender: " + sender.getMessageAddress() 
        + "\nReceiver: " + recipient.getMessageAddress()
        + "\nMessage Body: " + messageContent);
    }
}

class Twitter extends Message {
    public Twitter(Person sender, Person recipient, String messageContent) {
        super(sender, recipient, messageContent);
    }

    @Override
    public final void printMessage() {
        System.out.println("Sending to Twitter ======== "
        + "\nSender: " + sender.getMessageAddress() 
        + "\nReceiver: " + recipient.getMessageAddress()
        + "\nMessage Body: " + messageContent);
    }
}

// The Factory Method class
class MessageFactory {
    public final void sendMessageByPreference(Person sender, Person recipient, String messageContent) {
        Message newMessage;

        if (sender.getMessagingPreference() == Person.EMAIL) {
            newMessage = new Email(sender, recipient, messageContent);
            newMessage.printMessage();
        } else if (sender.getMessagingPreference() == Person.SMS) {
            newMessage = new SMS(sender, recipient, messageContent);
            newMessage.printMessage();
        } else if (sender.getMessagingPreference() == Person.TWITTER) {
            newMessage = new Twitter(sender, recipient, messageContent);
            newMessage.printMessage();
        } else {
            System.out.println("Invalid message preference");
        }
        System.out.println("\n");
    }
}

// The Chain of Responsibility interface and classes (it does chains of validations)
interface Chain {
    public void setNextChain(Chain nextChain);
    public boolean validate(Person sender, Person recipient, String messageContent);
}

class ValidateAddress implements Chain {
    private Chain nextChain;

    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    public boolean validate(Person sender, Person recipient, String messageContent) {
        if (sender.getMessageAddress() != null & recipient.getMessageAddress() != null) {
            nextChain.validate(sender, recipient, messageContent);
            return true;
        } else {
            return false;
        }
    }
}

class ValidateMessageContent implements Chain {
    private Chain nextChain;

    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    public boolean validate(Person sender, Person recipient, String messageContent) {
        if (messageContent.length() > 0) {
            nextChain.validate(sender, recipient, messageContent);
            return true;
        } else {
            return false;
        }
    }
}

// The Facade Pattern (or the interface which is a simple entry to the complex implementatin of the code)
// Also, the Chain of Responsibility is here since the Message has to go through chains of validations
class MessengerFacade {
    private final MessageFactory messageFactory = new MessageFactory();
    private final ValidateAddress validationAddress = new ValidateAddress();
    private final ValidateMessageContent validateMessage = new ValidateMessageContent();

    public final void sendMessage(Person sender, Person recipient, String messageContent) {
        validationAddress.setNextChain(validateMessage);

        // Here triggers the Chain of Responsibilty objects for validations
        if (validationAddress.validate(sender, recipient, messageContent)) {
            messageFactory.sendMessageByPreference(sender, recipient, messageContent);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Todo: Some logic done and found out the people below has been infected
        Person companyEmail = new Person("track-trace-email@mail.com", 1);
        Person companySMS = new Person("track-trace-sms@mail.com", 2);
        Person companyTwitter = new Person("track-trace-twitter@mail.com", 3);

        Person user1 = new Person("user1@mail.com", 1);

        MessengerFacade messengInterface = new MessengerFacade();

        messengInterface.sendMessage(companyEmail, user1, "Hello " + user1.getMessageAddress());
        messengInterface.sendMessage(companySMS, user1, "Hello " + user1.getMessageAddress());
        messengInterface.sendMessage(companyTwitter, user1, "Hello " + user1.getMessageAddress());
    }
}
