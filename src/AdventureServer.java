import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;

public final class AdventureServer implements AdventureServerAPI {
	/*
	 * Connection keeps track of important information about the connection:
	 *   connectionId - an unique identifier of the connection
	 *   socket - the actual connection
	 *   input - the input stream from the connection
	 *   output - the output stream send information to the other end.
	 *   adventureServer - an instance of the outer class.
	 */
	private class Connection {
		long connectionId = System.currentTimeMillis ( );
		Socket socket = null;
		BufferedReader input = null;
		PrintWriter output = null;
		AdventureServer adventureServer = null;

		public Connection ( AdventureServer adventureServer, Socket socket )
				  throws IOException {
			this.adventureServer = adventureServer;
			this.socket = socket;
			input = new BufferedReader (
					  new InputStreamReader ( socket.getInputStream ( ) ) );
			output = new PrintWriter ( socket.getOutputStream ( ), true );
		}
	}

	/*
	 * ClientThread is a Thread where the input from the connection is accepted.
	 * Accepted input is sent to registered ConnectionListener.
	 *
	 * The ClientThread allows for multiple connections.
	 */
	private class ClientThread extends Thread {
		Connection connection = null;

		public ClientThread ( Connection connection ) {
			try {
				this.connection = connection;
				start ( );
			} catch ( Exception e ) {
				e.printStackTrace ( );
			}
		}

		/*
		 * Loops over input from connection
		 * Signals the connection listener
		 */
		public void run ( ) {
			try {
				ConnectionEvent event = new ConnectionEvent ( ConnectionEventCode.CONNECTION_ESTABLISHED, connection.connectionId, "CONNECTION_ESTABLISHED" );
				connection.adventureServer.listener.handle ( event );
				String s = "";
				while ( connection.adventureServer.isRunning && ( s = connection.input
						                                                        .readLine ( ) ) != null ) {
					event = new ConnectionEvent ( ConnectionEventCode.TRANSMISSION_RECEIVED, connection.connectionId, s );
					connection.adventureServer.listener.handle ( event );
				}
				connection.input.close ( );
				connection.output.close ( );
				connection.socket.close ( );
			} catch ( SocketException e ) {
				// Hmmmm...
			} catch ( IOException e ) {
				e.printStackTrace ( );
			} finally {
				ConnectionEvent event = new ConnectionEvent ( ConnectionEventCode.CONNECTION_TERMINATED, connection.connectionId, "CONNECTION TERMINATED" );
				connection.adventureServer.connectionMap.remove ( connection.connectionId );
				connection.adventureServer.listener.handle ( event );
			}
		}
	}

	// Instance Variables
	private ConnectionListener listener = null;
	private int port = 2112;
	private boolean isRunning = false;
	private HashMap< Long, Connection > connectionMap = new HashMap<> ( );
	private ServerSocket serverSocket = null;

	/*
	 * Starts the server running
	 */
	public void startServer ( int port ) {
		this.port = port;
		if ( !isRunning ) {
			try ( ServerSocket server = new ServerSocket ( port ) ) {
				serverSocket = server;
				System.out.println( "waiting on " + getInetAddress () + " at " + getPort () );
				isRunning = true;
				while ( isRunning ) {
					Socket client = server.accept ( );
					Connection connection = new Connection ( this, client );
					connectionMap.put ( connection.connectionId, connection );
					System.out
							  .println ( "Connected to " + connection.connectionId + client.getInetAddress ( ) );
					new ClientThread ( connection );
				}
			} catch ( SocketException se ) {
				// Hmmm....
			} catch ( IOException e ) {
				e.printStackTrace ( );
			}
		}
	}

	/*
	 * return the port
	 */
	public int getPort( ) {
		return port;
	}

	/*
	 * return the server IP Address
	 */
	public String getInetAddress( ) throws UnknownHostException {
		return InetAddress.getLocalHost ( ).getHostAddress ( );
	}

	/*
	 * Stop the server
	 */
	public void stopServer ( ) {
		isRunning = false;
		try {
			serverSocket.close ();
		} catch ( IOException e ) {
			e.printStackTrace ( );
		}
	}

	/*
	 * Set the transmission listener
	 */
	public void setOnTransmission ( ConnectionListener listener ) {
		this.listener = listener;
	}

	/*
	 * return true if a connection exists
	 */
	public boolean isConnected( long connectionId ) {
		return ( connectionMap.get ( connectionId ) != null) ? true : false;
	}

	// Close the connection
	public void disconnect( long connectionId ) throws IOException,
	                                                   UnknownConnectionException {
		Connection connection = connectionMap.get ( connectionId );
		if ( connection != null ) {
			connection.socket.close ();
			connectionMap.remove ( connectionId );
		} else  {
			throw new UnknownConnectionException ( connectionId, "Unknown Connection: " + connectionId );
		}
	}

	// Change the connection ID - to facilitate save/load
	public void changeConnectionId( long connectionId, long newConnectionId ) throws
	                                                                          UnknownConnectionException {
		Connection connection = connectionMap.get ( connectionId );
		if ( connection != null ) {
			connectionMap.remove ( connectionId );
			connection.connectionId = newConnectionId;
			connectionMap.put ( newConnectionId, connection );
		} else  {
			throw new UnknownConnectionException ( connectionId, "Unknown Connection: " + connectionId );
		}
	}

	/*
	 * Send text through the connection.
	 */
	public void sendMessage( long connectionId, String message ) throws
	                                                             UnknownConnectionException {
		Connection connection = connectionMap.get ( connectionId );
		if ( connection != null ) {
			connection.output.println ( message );
			connection.output.flush ();
		} else {
			throw new UnknownConnectionException ( connectionId, "Unknown Connection: " + connectionId );
		}
	}

}
