import java.util.ArrayList;

public class Room {

    // Vores variable
    private String name;
    private String longDescription;
    private String shortDescription;
    private Boolean beenInRoomBefore = false;
    private Room north, east, south, west;
    private ArrayList<Item> items;


    // Konstruktør
    public Room (String name, String longDescription, String shortDescription) {
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.items = new ArrayList<>();
    }
    public ArrayList<Item> getItems(){
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItem(String shortName, String longName, String article) {
        items.add(new Item(shortName, longName, article));
    }

    public void addItem(String shortName, String longName, int healthPoints)
    {
        items.add(new Food(shortName, longName, healthPoints));
    }

    /*public void addItem(String shortName, String longName, int damage)
    {
        items.add(new MeleeWeapon(shortName, longName, damage));
    }*/

    public void addItem(String shortName, String longName, int damage, int ammunition)
    {
        items.add(new RangedWeapon(shortName, longName, damage, ammunition));
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item findItem(String shortName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(shortName)) {
                return item;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getDescription() {
        return beenInRoomBefore ? shortDescription : longDescription;
    }

    public void setBeenInRoomBefore() {
        this.beenInRoomBefore = true;
    }

    public void setNorth(Room room) {
        this.north = room;
    }

    public Room getNorth() {
        return north;
    }

    public void setEast(Room room) {
        this.east = room;
    }

    public Room getEast() {
        return east;
    }

    public void setSouth(Room room) {
        this.south = room;
    }

    public Room getSouth() {
        return south;
    }

    public void setWest(Room room) {
        this.west = room;
    }

    public Room getWest() {
        return west;
    }
}
