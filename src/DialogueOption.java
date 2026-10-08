public class DialogueOption {
    private String text;
    private DialogueNode nextNode;

    public DialogueOption(String text, DialogueNode nextNode)
    {
        this.text = text;
        this.nextNode = nextNode;
    }

    // TODO - tester hvid dialogue node font
    public String getText()
    {
        return "\033[0;97m" + text + "\u001b[0m";
    }

    public DialogueNode getNextNode()
    {
        return nextNode;
    }

    public boolean endsConversation()
    {
        return nextNode == null;
    }
}
