public class Player {

    private Room currentRoom;

    public Player (Room startingRoom) {
        currentRoom = startingRoom;
    }
public Room getCurrentRoom(){
        return currentRoom;
    }
    public boolean move(String direction) {

        Room nextRoom=
                switch (direction) {
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
