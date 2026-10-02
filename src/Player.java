import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int health;
    private Weapon equipped;

    public Player (Room startRoom) {
        this.currentRoom = startRoom;
        this.inventory = new ArrayList<>();
        this.health = 100;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    //
    public boolean takeItem(String shortName){
        // Objektreferencen gemmes i item først (så den ikke slettes)
        Item item = currentRoom.findItem(shortName);
        if (item == null) {
            return false;
        }
        currentRoom.removeItem(item);
        inventory.add(item);
        return true;
    }

    /*public ArrayList<Item> takeAllItems()
    {
        for (Item item : items) {
            item = currentRoom.getItem(item);
            if (item == null) {
                return false;
            }
            currentRoom.removeItem(item);
            inventory.add(item);
            *//*return true;*//*
        }
       return true;
    }*/

    /*public ArrayList<Item> getItems() {
        return items;
    }*/

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

    public ArrayList<Item> getInventory() {
        return inventory;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getHealth()
    {
        return health;
    }

    public EatResult eat(String shortName)
    {
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

    public WeaponResult equip(String shortName)
    {
        // IF: Tjek om item findes i inventory
        Item item = findItem(shortName); // først i inventory
        {
            if (item == null) {
            return WeaponResult.NOT_FOUND;
        }

        // IF-NOT weapon - sige det ikke er et våben
        // Hvis item IKKE er våben, return item_ikke våben
        if (!(item instanceof Weapon))
        {
            return WeaponResult.NOT_WEAPON;
        }

        equipped = (Weapon) item;

        return WeaponResult.IS_WEAPON;
        }
    }

    public void attack() {
        if (equipped == null) {
            System.out.println("Can't use that");
            return ;
        }

        if(!equipped.canUse()) {
            // "Out of ammo"
            System.out.println("Out of ammo");
            return;
        }

        equipped.use();

        // ramte noget med equipped.getDamage()
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

    public String look() {
        return currentRoom.getLongDescription();
    }
}
