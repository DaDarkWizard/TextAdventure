package Server.legacy;

/**
 * This is the exception thrown for unknown connections
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class UnknownConnectionException extends Exception {
    private long connectionId = 0L; //The connection ID in question

    // Constructor
    public UnknownConnectionException(long connectionId, String message) {
        super(message);
        this.connectionId = connectionId;
    }

    // Identifies the unknown connection that was requested
    public long getConnectionId() {
        return connectionId;
    }
}
