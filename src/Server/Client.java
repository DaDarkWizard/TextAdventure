package Server;

import Exceptions.ByteUnderflowException;
import Shared.coms.messages.ConnectionRequest;
import Shared.coms.messages.Message;
import Shared.coms.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private int connectionID;
    private Socket socket;
    boolean receivingMessage = false;
    private byte messageType = -1;
    private Message message;

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

    public void sendMessage(byte[] bytes) {
        try {
            socket.getOutputStream().write(bytes);
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
