/*
 * This is an example of how to run the adventure server.
 * Your game class parses and responds to input by handling ConnectionEvents.
 */
public class TechAdventureServerDemo implements ConnectionListener {
	AdventureServer adventureServer = null;

	public TechAdventureServerDemo ( ) {
		adventureServer = new AdventureServer ( );
		adventureServer.setOnTransmission ( this );
	}

	public void start( int port ) {
		adventureServer.startServer ( port );
	}

	@Override
	public void handle ( ConnectionEvent e ) {
		System.out.println( "EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
		System.out.println( String.format ( "connectionId=%d, data=%s", e.getConnectionID (), e.getData() ));
		try {
			switch ( e.getCode ( ) ) {
				case CONNECTION_ESTABLISHED:
					// What do you do when the connection is established?
					break;
				case TRANSMISSION_RECEIVED:
					adventureServer.sendMessage ( e.getConnectionID ( ), String.format (
							  "MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID ( ), e.getData ( ) ) );
					// BEWARE - if you keep this, any user can shutdown the server
					if ( e.getData ( ).equals ( "SHUTDOWN" ) ) {
						adventureServer.stopServer ( );
					}
					break;
				case CONNECTION_TERMINATED:
					// Cleanup when the connection is terminated.
					break;
				default:
					// What is a reasonable default?
			}
		} catch ( UnknownConnectionException unknownConnectionException ) {
			unknownConnectionException.printStackTrace ( );
		}
	}

	public static void main ( String[] args ) {
		TechAdventureServerDemo techAdventureServerDemo = new TechAdventureServerDemo ();
		techAdventureServerDemo.start ( 2112 );
	}

}
