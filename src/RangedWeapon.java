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
        /*System.out.println("You " + getAttackVerb() + " the " + shortName);*/
        ammunition--;
    }

    //
    public String getAttackVerb() {
        return "fire";
    }

    public String getUsesLeft() {
        return ammunition + " shots left";
    }

    /*@Override
    public String getAttackMessage(Enemy enemy) {
        return super.getAttackMessage(enemy) + " (" + getUsesLeft() + ")";
    }*/

    @Override
    public String getAttackMessage(String targetName, RollResult result){
        return switch (result) {
            case CRITICAL_HIT -> "You charge the stasis laser rifle to full capacity and breathe in deep. "
                                + "A blinding blue light erupts from the barrel and engulfs the " + targetName + ".";
            case HIT -> "The rifle hums and a pulse of blue light strikes the " + targetName + ".";
            case CRITICAL_FAIL -> "The rifle starts shaking, as you pull the trigger. The charge disappears.";

        };
    }

    public Sound getAudioType(Sound sound) {
        return Sound.LASER_SHOT_1;
    }
}
