import java.util.ArrayList;

public class Room {

    // Vores variable
    private String name;
    private String description;
    private String beenThereDescription;
    private Boolean beenInRoomBefore;
    private Room north, east, south, west;
    private ArrayList<Item> items;
    private Item item;


    // Konstruktør
    public Room (String name, String description, String beenThereDescription) {
        this.name = name;
        this.description = description;
        this.beenThereDescription = beenThereDescription;
        this.items = new ArrayList<>();
        this.beenInRoomBefore = false;
    }
    public ArrayList<Item> getItems(){
        return items;
    }

//    public List<Item> getImmutableItems() {
//        return Collections.unmodifiableList(items);
//    }
//
//    static void main(String[] args) {
//        new Room("", "", "").getImmutableItems().add(null);
//    }

    public void addItem(Item item) {
        items.add(item);
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

    public String getDescription() {
        if(beenInRoomBefore)
        {
            return beenThereDescription;
        }
        else {
            return description;
        }
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
