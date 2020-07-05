package Server;

import Exceptions.ByteUnderflowException;
import Shared.coms.messages.ConnectionRequest;
import Shared.coms.messages.Message;
import Shared.coms.MessageType;
import Shared.world.Chunk;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final int connectionID;
    private final Socket socket;
    boolean receivingMessage = false;
    private final byte messageType = -1;
    private Message message;
    private Chunk chunk;

    public Client(Socket socket, int connectionID) {
        this.socket = socket;
        this.connectionID = connectionID;
    }

    public int getConnectionID() {
        return this.connectionID;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void sendMessage(Message message) {
        try {
            message.write(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message receiveMessage() {
        return null;
    }

    public static void sendMessage(Client client, Message message) {

    }
}
