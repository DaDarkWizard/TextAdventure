package Server;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PlayerStorage {
    private static int max;
    private static final PriorityQueue<Integer> unusedSlots = new PriorityQueue<>();
    private static final HashMap<Integer, Client> players = new HashMap<>();
    private static final LinkedList<Client> toRemove = new LinkedList<Client>();


    public static int getMax() {
        return max;
    }

    public static int removeUsed() {
        if (unusedSlots.size() > 0) {
            return unusedSlots.poll();
        }
        else {
            return -1;
        }
    }

    public static void removePlayer(int connectionID) {
        if (players.containsKey(connectionID)) {
            Client player = players.remove(connectionID);
            unusedSlots.add(player.getConnectionID());
        }
    }

    public static void addClient(Client client) {
        players.put(client.getConnectionID(), client);
    }

    public static boolean isTracked(int connectionID) {
        return players.containsKey(connectionID);
    }

    public static Collection<Client> getClients() {
        return players.values();
    }
}
