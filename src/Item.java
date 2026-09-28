      public class Item {

      private String shortName;
      private String longName;

      UserInterface userInterface = new UserInterface();

      public Item(String shortName, String longName)
      {
          this.shortName = shortName;
          this.longName = longName;
      }

          public void printItemList(Item items) {

          }

          public String getLongName() {
              return longName;
          }
          public String getShortName() {
              return shortName;
          }
      }
