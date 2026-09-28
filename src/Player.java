import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item>inventory;
    /*UserInterface userInterface = new UserInterface();*/

    Item startItem1 = new Item("Flashlight", "Black flashlight with a blue glow");

    public Player (Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
        // Tester inventory metoder
        addItem(startItem1);
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    //
    public boolean takeItem(String shortName){
        Item item = currentRoom.findItem(shortName);
        if (item == null) {
            return false;
        }
        currentRoom.removeItem(item);
        inventory.add(item);
        return true;
    }

    public Boolean dropItem(String shortName){
        Item item = findItem(shortName);
        if (item == null) {
            return false;
        }
        currentRoom.addItem(item);
        inventory.remove(item);
        return true;
    }

    public Item findItem(String shortName){
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    /*public Item getInventory() {
        for (Item item : inventory) {
            userInterface.printMessage(item.getShortName());
        }
        System.out.println("Der er intet i inventory");
        return null;
    }*/

    public ArrayList<Item> getInventory() {
        return inventory;
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
