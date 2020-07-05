package Server.legacy;

import java.io.IOException;
import java.net.*;

public interface AdventureServerAPI {
    // Starts the server running
    void startServer(int port);

    // Stop the server
    void stopServer();

    // Set the transmission listener
    void setOnTransmission(ConnectionListener listener);

    // Send text through the connection.
    void sendMessage(long connectionId, String message) throws
            UnknownConnectionException;

    // return true if a connection exists
    boolean isConnected(long connectionId);

    // Close the connection
    void disconnect(long connectionId) throws IOException,
            UnknownConnectionException;

    // Change the connection ID - to facilitate save/load
    void changeConnectionId(long connectionId, long newConnectionId) throws
            UnknownConnectionException;

    // return the port
    int getPort();

    // return the server IP Address
    String getInetAddress() throws UnknownHostException;
}
