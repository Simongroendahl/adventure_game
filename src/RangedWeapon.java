public class RangedWeapon extends Weapon{
    private int ammunition;

    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        super(shortName, longName, damage);
        this.ammunition = ammunition;
    }

    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    // Equip item
    public void use() {
        ammunition--;
    }

    //
    public String getAttackVerb() {
        return "You fire the " + shortName;
    }

    public String getUsesLeft() {
        return ammunition + " shots left.";
    }
}
