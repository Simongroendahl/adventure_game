public abstract class Weapon extends Item {

    private int damage;

    public Weapon(String shortName, String longName, int damage) {
        super(shortName, longName);
        this.damage = damage;
    }

    // Metoder
    public int getDamage() {
        return damage;
    }

    public abstract boolean canUse();

    // Equip item
    public abstract void use();

    public String getAttackVerb() {
        return "";
    }

    public String getUsesLeft() {
        return "";
    }

    public String getAttackMessage(String targetName, RollResult result) {
        return switch (result) {
            case CRITICAL_HIT  -> "You " + getAttackVerb() + " the " + targetName
                    + " with the " + getShortName() + ". A perfect hit!";
            case HIT           -> "You " + getAttackVerb() + " the " + targetName
                    + " with the " + getShortName() + ".";
            case CRITICAL_FAIL -> "You try to " + getAttackVerb() + " the " + targetName
                    + " with the " + getShortName() + ", but miss completely.";
        };
    }

    public String getEnemyAttackMessage(String attackerName, RollResult result) {
        return switch (result) {
            case CRITICAL_HIT  -> "The " + attackerName + " strikes you with its " + getShortName()
                    + " with terrible force!";
            case HIT           -> "The " + attackerName + " attacks you with its " + getShortName() + ".";
            case CRITICAL_FAIL -> "The " + attackerName + " attacks you with its " + getShortName()
                    + " but misses.";
        };
    }
}
