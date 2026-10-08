import java.util.ArrayList;

public class Room {

    // Vores variable
    private String name;
    private String longDescription;
    private String shortDescription;
    private Sound roomAudio;
    private Boolean beenInRoomBefore = false;
    private String requiredItemName;
    private String password;
    private boolean unlocked;
    public static final int MAX_PASSWORD_ATTEMPTS = 3;
    private int failedAttempts = 0;

    private Room north, east, south, west;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;
    private DialogueNode terminalDialogue;


    // Konstruktør
    public Room (String name, String longDescription, String shortDescription, Sound roomAudio) {
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.roomAudio = roomAudio;
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void setRoomAudio(Sound roomAudio) {
        this.roomAudio = roomAudio;
    }

    public Sound getRoomAudio() {
        return roomAudio;
    }

    public void setRequiredItem(String itemName){
        this.requiredItemName = itemName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean needsPassword(){
        return password != null && !unlocked;
    }

    public boolean tryPassword(String attempt){
        if (password != null && password.equalsIgnoreCase(attempt.trim())){
            unlocked = true;
            return true;
        }
        failedAttempts++;
        return false;
    }

    public int getAttemptsLeft() {
        return MAX_PASSWORD_ATTEMPTS - failedAttempts;
    }

    public boolean isLockedFor(Player player) {
        return requiredItemName != null && player.findItem(requiredItemName) == null;
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

    public void setTerminalDialogue(DialogueNode node) {
        terminalDialogue = node;
    }

    public DialogueNode getTerminalDialogue() {
        return terminalDialogue;
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

    // ENEMY metoder
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public Enemy findEnemy(String shortName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().trim().equalsIgnoreCase(shortName)) {
                return enemy;
            }
            else if(shortName.isEmpty()){
                return enemies.get(0);
            }
        }
        return null;
    }
}
