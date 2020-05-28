package Server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PlayerStorage {
    private static int max;
    private static PriorityQueue<Integer> unusedSlots = new PriorityQueue<>();
    private static HashMap<Integer, Client> players = new HashMap<>();
    private static LinkedList<Client> toRemove = new LinkedList<Client>();


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
}
