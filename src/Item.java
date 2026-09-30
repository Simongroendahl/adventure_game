public class Item {

    protected String shortName;
    protected String longName;
    private String article;

    public Item(String shortName, String longName) {
        this(shortName, longName, guessArticle(longName));
    }

    public Item(String shortName, String longName, String article) {
        this.shortName = shortName;
        this.longName = longName;
        this.article = article;
    }

    private static String guessArticle(String longName) {
        return "aeiou".indexOf(Character.toLowerCase(longName.charAt(0))) >= 0 ? "an" : "a";
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getIndefiniteName() {
        return article.isEmpty() ? longName : article + " " + longName;
    }

    public String getDefiniteName() {
        return article.isEmpty() ? longName : "the " + longName;
    }
}
