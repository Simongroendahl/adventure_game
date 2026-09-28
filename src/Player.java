import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item>inventory;
    UserInterface userInterface = new UserInterface();


    public Player (Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public Item takeItem(String shortName){
        /*currentRoom.getItems().remove(shortName);
        inventory.add();*/
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                inventory.add(item);
            }
        }
        return null;
    }

    public Item dropItem(String shortName){
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                inventory.remove(item);
            }
        }
        return null;
    }

    public Item findItem(String shortName){
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    public Item getInventory() {
        for (Item item : inventory) {
            userInterface.printMessage(item.getShortName());
        }
        System.out.println("Der er intet i inventory");
        return null;
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
