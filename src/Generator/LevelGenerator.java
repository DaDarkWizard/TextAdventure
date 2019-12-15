package Generator;

import GamePieces.Room;
import PlayerHandler.Commands;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles procedural dungeon generation
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class LevelGenerator {
    private Room start;
    private Room end;
    private ArrayList<Room> rooms = new ArrayList<>();
    private int maxRooms;
    private int roomCount = 0;
    private int tries = 0;
    private Room[][] roomMatrix;
    private int size;
    private RoomGenerator roomGenerator;

    /**
     * Room generation constructor
     * @param size
     * @param count
     */
    public LevelGenerator(int size, int count) {
        this.maxRooms = count;
        this.size = size;
        roomGenerator = new RoomGenerator();
        generateRooms();
    }

    public Room getEnd() {
        return end;
    }

    public Room getStart() {
        return start;
    }

    /**
     * Sets and fills parameters of generic room creation
     */
    private void generateRooms() {
        while (tries < 100 && roomCount < maxRooms) {
            setupGeneration();

            int y = size / 2;
            int x = size / 2;
            boolean deadEnd = false;

            while (roomCount < maxRooms && !deadEnd) {
                //create a new room
                Room newRoom = createRoom();

                //place it at x,y
                roomMatrix[x][y] = newRoom;

                //connect it to other directions
                //connect north
                tryConnect(Commands.north, x, y);
                //connect south
                tryConnect(Commands.south, x, y);
                //connect east
                tryConnect(Commands.east, x, y);
                //connect west
                tryConnect(Commands.west, x, y);

                //Increment count
                roomCount++;
                rooms.add(newRoom);

                //find a new direction
                Commands direction = findDirection(x, y);
                if (direction == null) {
                    deadEnd = true;
                } else {
                    switch (direction) {
                        //set x,y = to the location
                        case north:
                            y = y - 1;
                            break;
                        case south:
                            y = y + 1;
                            break;
                        case east:
                            x = x + 1;
                            break;
                        case west:
                            x = x - 1;
                            break;
                    }
                }
            }
            this.start = rooms.get(0);
            this.end = rooms.get(rooms.size() - 1);
            tries++;
        }

        start = rooms.get(0);
        end = rooms.get(rooms.size() - 1);

        if (tries >= 10 && roomCount < maxRooms) {
            System.out.println("FAILED!");
            this.end = null;
            this.start = null;
            rooms.clear();
        } else {
            roomGenerator.makeEndRoom(end);
            roomGenerator.makeStartRoom(start);
        }
    }

    /**
     * Prints dungeon schematics to screen
     * @param rooms
     */
    public static void printRooms(Room[][] rooms) {
        System.out.println("[");
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] != null) {
                    System.out.print("1,");
                } else {
                    System.out.print("0,");
                }
            }
            System.out.println();
        }
        System.out.println("]");
    }

    /**
     * Helper function to randomly grab an open spot for the new room adjacent to the one whose coords are given
     *
     * @param x X coord of room
     * @param y Y coord of room
     * @return new direction, or null if it can't find one
     */
    private Commands findDirection(int x, int y) {
        ArrayList<Commands> directions = new ArrayList<>();
        directions.add(Commands.north);
        directions.add(Commands.south);
        directions.add(Commands.east);
        directions.add(Commands.west);

        if (roomMatrix[x][y].getNorth() != null || y - 1 < 0) {
            directions.remove(Commands.north);
        }
        if (roomMatrix[x][y].getSouth() != null || y + 1 >= size) {
            directions.remove(Commands.south);
        }
        if (roomMatrix[x][y].getEast() != null || x + 1 >= size) {
            directions.remove(Commands.east);
        }
        if (roomMatrix[x][y].getWest() != null || x - 1 < 0) {
            directions.remove(Commands.west);
        }

        if (directions.size() < 1) {
            return null;
        }

        return directions.get(((int) (Math.random() * directions.size())));
    }

    /**
     * Helper function for connecting rooms
     *
     * @param direction direction to attempt
     * @param x         x coord of room
     * @param y         y coord or room
     */
    private void tryConnect(Commands direction, int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            throw new IllegalArgumentException("X and Y must be in bounds!");
        }
        if (roomMatrix[x][y] == null) {
            throw new IllegalArgumentException("Room must not be null!");
        }
        switch (direction) {
            case north:
                if (y - 1 < 0 || roomMatrix[x][y - 1] == null) {
                    return;
                }
                roomMatrix[x][y].setNorth(roomMatrix[x][y - 1]);
                roomMatrix[x][y - 1].setSouth(roomMatrix[x][y]);
                break;
            case east:
                if (x + 1 >= size || roomMatrix[x + 1][y] == null) {
                    return;
                }
                roomMatrix[x][y].setEast(roomMatrix[x + 1][y]);
                roomMatrix[x + 1][y].setWest(roomMatrix[x][y]);
                break;
            case west:
                if (x - 1 < 0 || roomMatrix[x - 1][y] == null) {
                    return;
                }
                roomMatrix[x][y].setWest(roomMatrix[x - 1][y]);
                roomMatrix[x - 1][y].setEast(roomMatrix[x][y]);
                break;
            case south:
                if (y + 1 >= size || roomMatrix[x][y + 1] == null) {
                    return;
                }
                roomMatrix[x][y].setSouth(roomMatrix[x][y + 1]);
                roomMatrix[x][y + 1].setNorth(roomMatrix[x][y]);
                break;
            default:
                throw new IllegalArgumentException("Must be a direction!");
        }
    }

    private Room createRoom() {
        //Todo setup createRoom
        return roomGenerator.generateRoom();
    }

    private void setupGeneration() {
        roomMatrix = new Room[size][size];
        roomCount = 0;
        start = null;
        end = null;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                roomMatrix[i][j] = null;
            }
        }

        rooms.clear();
    }
}
