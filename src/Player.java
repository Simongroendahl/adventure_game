public class Player {

    private Room currentRoom;

    public Player (Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room move(String direction) {
        Room nextRoom = switch (direction.trim().toLowerCase()) {
            case "north", "go north", "n" -> currentRoom.getNorth();
            case "south", "go south", "s" -> currentRoom.getSouth();
            case "east", "go east", "e"  -> currentRoom.getEast();
            case "west", "go west", "w"  -> currentRoom.getWest();
            default -> null;
        };

        if (nextRoom != null) {
            currentRoom = nextRoom;
        }
        return nextRoom;
    }
}
