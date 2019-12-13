package Generator;

import GamePieces.Room;
import PlayerHandler.Commands;

import java.util.ArrayList;


public class RoomGenerator {
    private Room start;
    private Room end;
    private ArrayList<Room> rooms = new ArrayList<>();
    private int maxRooms;
    private int roomCount = 0;
    private int tries = 0;
    private Room[][] roomMatrix;
    private int size;

    public RoomGenerator(int size, int count) {
        this.maxRooms = count;
        this.size = size;
        generateRooms();
    }

    private void generateRooms() {
        while (tries < 10 && roomCount < maxRooms) {
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

                //Todo find a new direction
                Commands direction = findDirection(newRoom, x, y);
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

            tries++;
        }

        start = rooms.get(0);
        end = rooms.get(rooms.size() - 1);

        if (tries >= 10 && roomCount < maxRooms) {
            System.out.println("FAILED!");
        }
    }

    private Commands findDirection(Room room, int x, int y) {
        ArrayList<Commands> directions = new ArrayList<>();
        directions.add(Commands.north);
        directions.add(Commands.south);
        directions.add(Commands.east);
        directions.add(Commands.west);

        if (room.getNorth() != null || y - 1 < 0) {
            directions.remove(Commands.north);
        }
        if (room.getSouth() != null || y + 1 >= size) {
            directions.remove(Commands.south);
        }
        if (room.getEast() != null || x + 1 >= size) {
            directions.remove(Commands.east);
        }
        if (room.getWest() != null || x - 1 >= size) {
            directions.remove(Commands.west);
        }

        if (directions.size() < 1) {
            return null;
        }

        return directions.get(((int) (Math.random() * directions.size())) + 1);
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
        return null;
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
