public class UnknownConnectionException extends Exception {
	private long connectionId = 0L;

	// Constructor
	public UnknownConnectionException ( long connectionId, String message ) {
		super( message );
		this.connectionId = connectionId;
	}

	// Identifies the unknown connection that was requested
	public long getConnectionId ( ) {
		return connectionId;
	}
}
