public class CombatOption {

    private String label;
    private CombatAction action;
    private Enemy target;
    private String targetName;

    public CombatOption(String label, CombatAction action) {
        this(label, action, null, null);
    }

    public CombatOption(String label, CombatAction action, Enemy target, String targetName) {
        this.label = label;
        this.action = action;
        this.target = target;
        this.targetName = targetName;
    }

    public String getLabel() {
        return label;
    }

    public CombatAction getAction() {
        return action;
    }

    public Enemy getTarget() {
        return target;
    }

    public String getTargetName() {
        return targetName;
    }


}
