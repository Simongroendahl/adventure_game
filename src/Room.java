public class Room {

    // Vores variable
    private String name;
    private String description;
    private String beenThereDescription;
    private Boolean beenInRoomBefore;
    private Room north, east, south, west;

    // Konstruktør
    public Room (String name, String description, String beenThereDescription) {
        this.name = name;
        this.description = description;
        this.beenThereDescription = beenThereDescription;
        this.beenInRoomBefore = false;
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
