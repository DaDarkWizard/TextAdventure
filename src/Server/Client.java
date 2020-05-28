package Server;

import Exceptions.ByteUnderflowException;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private int connectionID;
    private Socket socket;
    boolean receivingMessage = false;
    private byte messageType = -1;

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

    public byte[] receiveMessage() {
        try {
            if (receivingMessage) {
                return null;
            } else { //Try to begin receiving a message.
                //Check if bytes are available
                if (socket.getInputStream().available() > 0) {
                    //Create array to store first byte
                    byte[] _temp = new byte[1];
                    int readBytes = socket.getInputStream().read(_temp);
                    if(readBytes < 1) {
                        throw new ByteUnderflowException();
                    }
                    messageType = _temp[0];
                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
