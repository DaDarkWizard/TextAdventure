public class ConnectionEvent {
	private ConnectionEventCode code = null;
	private long connectionID = 0L;
	private String data = null;

	// Constructor
	public ConnectionEvent ( ConnectionEventCode code, long connectionID, String data ) {
		this.code = code;
		this.connectionID = connectionID;
		this.data = data;
	}

	// The reason for the event
	public ConnectionEventCode getCode ( ) {
		return code;
	}

	// The ID of the connection associated with the event.
	public long getConnectionID ( ) {
		return connectionID;
	}

	// A text message associated with the event.
	public String getData ( ) {
		return data;
	}
}
