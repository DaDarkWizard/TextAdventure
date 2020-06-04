package Server;

import Shared.coms.messages.Message;
import Shared.util.Vector;

public class Player {
    private Vector location;
    private Vector velocity;
    private Client client;

    public Player(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void sendMessage(Message message) {
        int height = 1;
        int width = 1;
    }

    public void receiveMessage(Message message) {
        this.client.sendMessage(message);
    }
}
