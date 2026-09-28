import java.util.ArrayList;

public class Player {

    private Room currentRoom;

    private ArrayList<Item>inventory;


    public Player (Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory=new ArrayList<>();
    }
    public void addItem(Item item){
        inventory.add(item);
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public void takeItem(String shortName){

        currentRoom.getItems().remove(shortName);
        inventory.add(shortName);
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
