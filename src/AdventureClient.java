import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class AdventureClient {
	public static void main ( String[] args ) {
		if ( args.length != 2 ) {
			System.out.println( "Command line arguments: server_address port" );
		} else {
			try ( Socket server = new Socket ( args[0], Integer.valueOf ( args[1] ) ) ) {
				System.out.println( "Connected to AdventureServer host " + server.getInetAddress () );
				BufferedReader fromServer = new BufferedReader ( new InputStreamReader ( server.getInputStream () ) );
				PrintWriter toServer = new PrintWriter ( server.getOutputStream (), true );
				BufferedReader keyboardInput = new BufferedReader ( new InputStreamReader ( System.in ) );
				String s = "";
				while (true) {
					System.out.print("> ");
					System.out.flush ();
					if ((s=keyboardInput.readLine ()) == null) { break; }
					toServer.println ( s );
					s = fromServer.readLine ();
					if (s==null) {break;}
					System.out.println ( s );
				}
				fromServer.close ();
				toServer.close ();
				keyboardInput.close ();
			} catch ( UnknownHostException e ) {
				e.printStackTrace ( );
			} catch ( IOException e ) {
				e.printStackTrace ( );
			}
		}
	}
}
