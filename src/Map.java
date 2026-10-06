public class Map {

    Room room1 = new Room("ROOM 1 - THE SLEEPING PODS",
            """
            You are in a sleeping pod chamber. There are five more sleeping pods, but all of them are empty. 
            There are two doors: one facing east with a key card terminal; sparks are flying from the door facing south.
            """,
            "You're back in the sleeping pod chamber");
    Room room2 = new Room("ROOM 2 - THE HALLWAY", """
            *** ACCESS GRANTED *** The door opens, the key card worked. \nA loud alarm is blaring through the speakers. The hallway is dark, only lit up by waves of red light from the alarm. 
            You see a door at the end of the hallway closing. A dark shadow runs through it. 
            A window is on your right that shows the escape pod room to the south. (go east / go west)""",
            "You're back in the hallway.");
    Room room3 = new Room("ROOM 3 - THE DINING ROOM", """
    You are in a dining room. The place is completely empty - except for a plate with a fresh burger on it on the dining table. 
    There is a door to the south. (go south / go west)""",
            "You're back in the dining room.");
    Room room4 = new Room("ROOM 4 - THE OFFICE ROOM", """
    As the door opens, you're met with a sight you can't understand. 
    A huge mass takes up most of the room space. Massive tentacles surrounding it move idly. It looks asleep. 
    In the far corner, you see your old desk. You know the password for the escape pods might be in the top drawer.""",
            "You're back in the office room. The mass is not moving.");
    Room room5 = new Room("ROOM 5 - THE ESCAPE POD", """
            As you run inside the room, the doors rapidly shut behind you.
            You run to the console and with shaking hands type in your destination.
            You sit down on the seat, tighten the seatbelt, and feel a sudden bump as the pod detaches itself.
            The door that barely held out the aliens slowly opens, and you see tentacles poke through.
            You made it.""",
            "You're back in the escape pod.");

    Room room6 = new Room("ROOM 6 - THE HALLWAY WITH WINDOWS", """
    Huge windows cover the eastern side of the hallway walls. You look out at the vast space, sprinkled with stars and planets you have never seen before. 
    A sudden knock on the window draws your attention. 
    You see a person in a spacesuit floating in the dark. There's a huge hole in the helmet, with red liquid around the broken glass""",
            "You're back in the hallway.");

    Room room7 = new Room("ROOM 7 - THE CHANGING ROOM", """
    A thick steam rolls out the hallway, as you enter. Every shower is running. It's hard to hear anything but the dripping water. 
    You look down and see red liquid flush down the drain.""",
            "You're back in the steam-filled hallway. And something is moving slowly, intentionally, in the steam.");

    Room room8 = new Room("ROOM 8 - THE MONITORING ROOM", """
    There are strange machines, with surveillance video on seven of the nine screens, temperature and heart rate monitoring.
    By the door facing north, you see a terminal under the "ESCAPE POD" room sign. The screen flickers, but reads: "ENTER PASSWORD" """,
            "You're back in the monitoring room.");

    Room room9 = new Room("ROOM 9 - THE LABORATORY", """
    An immediate horrible smell fills the room, as the door opens. You see a familiar face. But it's not where it belongs. 
    Stuck on the walls, you see several people you used to remember. 
    The medic, the mechanic, and then you see the face you hoped not to see - your wife's. 
    They are covered in organic matter. Their bellies are hanging out, and look extremely big. Something is moving inside of them.""",
            "You're back in the laboratory.");


    /*Food burger = new Food("burger", "a fresh hamburger", 10);*/

    Weapon[] weapons = {
            new MeleeWeapon("wrench", "old, rusty wrench", 20),
            new RangedWeapon("laser rifle", "stasis laser rifle", 30, 5),
            new MeleeWeapon("tentacle", "sharp tentacle", 20)
    };

    Enemy[] enemies = {
            new Enemy("alien", "asdf", "description", 50, weapons[2], room1)
    };

     public void buildItems(){
         // Kalder den overloadede addItem metode fra Room klassen.
         room1.addItem("key card", "bloodied key card", "a");

         room2.addItem("flash light", "robust flash light", "a");

         room1.addItem(weapons[0]);
         room2.addItem(weapons[1]);

         room3.addItem("burger", "a fresh burger", 10);
         room3.addItem("battery", "smart space rocket laser battery", -99);
         room3.addItem("cola", "fresh, ice-cold bottle of cola", "a");

         room9.addItem("stim pack", "a stim pack for medical emergencies", 20);
     }

     public void buildEnemies() {
         room1.addEnemy(enemies[0]);
     }

     public void buildDialogue() {
         // BUILD TERMINAL
         DialogueNode start = new DialogueNode("TERMINAL:", "Welcome, operator. How can I assist?");
         DialogueNode shipInfo = new DialogueNode("TERMINAL:", "The Hermes II is 112 years into its [REDACTED] voyage. \nSent by HomeStead Inc in search for [REDACTED] deep space specimens, artifacts, and highly [REDACTED] organic materials.");
         DialogueNode warning = new DialogueNode("TERMINAL:", "***WARNING*** Sensors detect another life signature onboard.");
         DialogueNode crewStatus = new DialogueNode("TERMINAL:", "Crew Status: Error.");
         DialogueNode shipStatus = new DialogueNode("TERMINAL:", "What would you like to check?");
         DialogueNode engineStatus = new DialogueNode("TERMINAL:", "ENGINE STATUS: Fully functioning - Engine at full capacity.");
         DialogueNode crewErrorMessage = new DialogueNode("TERMINAL:", "Can't read bio-metrics of 10 out of 11 employees. Please consult an engineer for error finding.");

         // Start options
         start.addOption("Tell me about the ship.", shipInfo);
         start.addOption("Check ship status", shipStatus);
         start.addEndOption("Log off.");

         shipInfo.addOption("Go back.", start);
         shipInfo.addEndOption("Log off.");

         shipStatus.addOption("Check engine status.", engineStatus);
         shipStatus.addOption("Check crew status.", crewStatus);

         engineStatus.addOption("Go back", shipStatus);
         engineStatus.addEndOption("Log off.");

         warning.addOption("Go back.", start);
         warning.addEndOption("Log off.");

         crewStatus.addOption("Read error message", crewErrorMessage);
         crewStatus.addOption("Go back", start);
         crewStatus.addEndOption("Log off.");

         crewErrorMessage.addOption("Go back", start);
         crewErrorMessage.addEndOption("Log off.");

         room1.setTerminalDialogue(start);
     }



    public Room getStartRoom() {
        return room1;
    }

    public void buildMap() {
        /*room1.setEast(room2);*/
        room1.lockEast(room2);
        room1.setSouth(room4);

        room2.setWest(room1);
        room2.setEast(room3);

        room3.setWest(room2);
        room3.setSouth(room6);

        room4.setNorth(room1);
        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setNorth(room3);
        room6.setSouth(room9);

        room7.setNorth(room4);
        room7.setEast(room8);

        room8.setWest(room7);
        room8.lockNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);
    }

    public void openRoomTwo() {
         room1.setEast(room2);
    }
}
