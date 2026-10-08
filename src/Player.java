import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;
    private Weapon equipped;

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
        this.health = 100;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    //
    public boolean takeItem(String shortName) {
        // Objektreferencen gemmes i item først (så den ikke slettes)
        Item item = currentRoom.findItem(shortName);
        if (item == null) {
            return false;
        }
        currentRoom.removeItem(item);
        addItem(item);
        return true;
    }

    public ArrayList<Item> takeAllItems() {
        ArrayList<Item> taken = new ArrayList<>(currentRoom.getItems());

        for (Item item : taken) {
            currentRoom.removeItem(item);
            addItem(item);
        }
        return taken;
    }

    public Boolean dropItem(String shortName) {
        Item item = findItem(shortName);
        if (item == null) {
            return false;
        }

        if (item == equipped) {
            equipped = null;
        }

        currentRoom.addItem(item);
        removeItem(item);
        return true;
    }

    public boolean hasItem(String i) {
        return inventory.contains(i);
    }

    public Item findItem(String shortName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth() {
        return health;
    }

    public EatResult eat(String shortName) {
        Item item = findItem(shortName);                // først i inventory
        if (item == null) {
            item = currentRoom.findItem(shortName);     // så i rummet
        }

        if (item == null) {
            return EatResult.NOT_FOUND;
        }
        if (!(item instanceof Food)) {
            return EatResult.NOT_FOOD;
        }

        Food food = (Food) item;
        health += food.getHealthPoints();
        removeItem(food);                               // maden forsvinder – fra inventory
        currentRoom.removeItem(food);                   // eller fra rummet
        return EatResult.EATEN;
    }

    public Weapon getEquipped() {
        return equipped;
    }

    public WeaponResult equip(String shortName) {
        Item item = findItem(shortName); // først i inventory
        {
            if (item == null) {
                return WeaponResult.NOT_FOUND;
            }

            if (!(item instanceof Weapon)) {
                return WeaponResult.NOT_WEAPON;
            }

            equipped = (Weapon) item;
            return WeaponResult.IS_WEAPON;
        }
    }

    public void hit(int damage) {
        health -= damage;
    }

    public Room getRoomInDirection(String direction) {
        return switch (direction.trim().toLowerCase()) {
            case "north", "go north", "n" -> currentRoom.getNorth();
            case "south", "go south", "s" -> currentRoom.getSouth();
            case "east", "go east", "e" -> currentRoom.getEast();
            case "west", "go west", "w" -> currentRoom.getWest();
            default -> null;
        };
    }

    public MoveResult move(String direction) {
        Room nextRoom = getRoomInDirection(direction);

        if (nextRoom == null) {
            return MoveResult.NO_EXIT;
        }

        if (nextRoom.needsPassword()) {
            return MoveResult.NEEDS_PASSWORD;
        }

        if (nextRoom.isLockedFor(this)) {
            return MoveResult.LOCKED;
        }

        currentRoom = nextRoom;
        return MoveResult.MOVED;
    }

    public String look() {
        return currentRoom.getLongDescription();
    }
}
