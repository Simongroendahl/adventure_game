import java.util.ArrayList;

public class DialogueNode {

    private String speaker;
    private String text;
    private ArrayList<DialogueOption> options;

    public DialogueNode(String speaker, String text)
    {
        this.text = text;
        this.speaker = speaker;
        this.options = new ArrayList<>();
    }

    public void addOption(String optionText, DialogueNode nextNode)
    {
        options.add(new DialogueOption(optionText, nextNode));
    }

    public void addEndOption(String optionText)
    {
        options.add(new DialogueOption(optionText, null));
    }

    public String getText()
    {
        return text;
    }

    public String getSpeaker()
    {
        return speaker;
    }

    public ArrayList<DialogueOption> getOptions()
    {
        return options;
    }
}
