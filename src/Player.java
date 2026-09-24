public class Player {

    private static Room currentRoom;

    public Player (Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public static boolean move(String direction) {

        Room nextRoom = switch (direction) {
            case "north", "go north", "n" -> currentRoom.getNorth();
            case "south", "go south", "s" -> currentRoom.getSouth();
            case "east", "go east", "e"  -> currentRoom.getEast();
            case "west", "go west", "w"  -> currentRoom.getWest();
            default -> null;
        };

        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDescription());
            currentRoom.setBeenInRoomBefore();
            return true;
        }
        else {
            return false;
        }
    }
}
