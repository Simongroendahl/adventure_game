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

    public String getAttackMessage(Enemy enemy) {
        return "You " + getAttackVerb() + " the " + shortName + " at the " + enemy.getShortName() + " for " + getDamage() + " damage.";
    }
}
