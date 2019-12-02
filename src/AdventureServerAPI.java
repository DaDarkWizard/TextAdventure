import java.io.IOException;
import java.net.*;

public interface AdventureServerAPI {
	// Starts the server running
	public void startServer ( int port );

	// Stop the server
	public void stopServer ( );

	// Set the transmission listener
	public void setOnTransmission ( ConnectionListener listener );

	// Send text through the connection.
	public void sendMessage( long connectionId, String message ) throws
	                                                             UnknownConnectionException;

	// return true if a connection exists
	public boolean isConnected( long connectionId );

	// Close the connection
	public void disconnect( long connectionId ) throws IOException,
	                                                   UnknownConnectionException;

	// Change the connection ID - to facilitate save/load
	public void changeConnectionId( long connectionId, long newConnectionId ) throws
	                                                                          UnknownConnectionException;

	// return the port
	public int getPort( );

	// return the server IP Address
	public String getInetAddress( ) throws UnknownHostException;
}
