package Server;

import Shared.coms.MessageType;

import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        if (args.length < 1) {

        }
    }



    public Server(int port) {

    }

    private class Connection {
        public long connectionID = System.currentTimeMillis();

        public Connection(Socket socket) {
            //MessageType.message.saved = false;
        }
    }
}
