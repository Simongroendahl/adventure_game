public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    // Metoder
    @Override
    public boolean canUse() {
        return true;
    }

    // Equip item
    public void use() {
        /*System.out.println("You " + getAttackVerb() + " the " + shortName);*/
    }

    //
    public String getAttackVerb() {
        return "swing";
    }
}
