package Server;

import Shared.coms.messages.ConnectionApproved;
import Shared.coms.messages.ConnectionRequest;
import Shared.coms.messages.FatalError;
import Shared.coms.messages.Message;
import Shared.coms.MessageType;

import java.util.ArrayList;
import java.util.LinkedList;

public class MessageHandler {
    private static LinkedList<Message> messages = new LinkedList<>();

    public static void addMessage(Message message) {
        messages.add(message);
    }

    public static Message firstMessage() {
        return messages.removeFirst();
    }

    public static void checkForMessages() {
        Message message;
        for (Client client : PlayerStorage.getClients()) {
            message = client.receiveMessage();
            if (message == null) {
                break;
            }

            switch (MessageType.getMessageType(message.getMessageId())) {
                case CONNECTION_REQUEST:
                    handleConnectionRequest(client, (ConnectionRequest) message);
                    break;
                case UNKNOWN_TYPE:
                    break;
            }
            message = null;
        }
    }

    public static void handleConnectionRequest(Client client, ConnectionRequest message) {
        if (message.getVersion().equals(Server.version)) {
            ConnectionApproved out = new ConnectionApproved(client.getConnectionID());
            Client.sendMessage(client, out);
        } else {
            FatalError out = new FatalError("Incorrect version! Correct version: " + Server.version);
            Client.sendMessage(client, out);
        }
    }
}
