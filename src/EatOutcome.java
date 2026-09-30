public class EatOutcome {
    private final EatResult result;
    private final String itemName;   // tingens lange navn (null hvis den ikke blev fundet)
    private final int healthChange;  // 0 hvis intet blev spist

    public EatOutcome(EatResult result, String itemName, int healthChange) {
        this.result = result;
        this.itemName = itemName;
        this.healthChange = healthChange;
    }

    public EatResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealthChange() {
        // If statement ser om Food giver liv eller tager liv
        // getHealth() -- fra Player klassen

        // getHealthPoints() -- fra Food klassen
        // Sum de to mod hinanden

        // return healthChange
        return healthChange;
    }
}
