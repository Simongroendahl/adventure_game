public class Map {

    Room room1 = new Room("SECTOR 1 - THE SLEEPING PODS",
            """
            You are in a sleeping pod chamber. There are ten more sleeping pods, but all of them are empty. 
            There are two doors: one facing east with a key card terminal; sparks are flying from the door facing south.""",
            "You're back in the sleeping pod chamber");
    Room room2 = new Room("SECTOR 2 - THE HALLWAY", """
            *** ACCESS GRANTED ***\nA loud alarm is blaring through the speakers. The hallway is dark, only lit up by waves of red light from the alarm. 
            You see a door at the end of the hallway closing. A dark shadow runs through it. 
            A window is on your right that shows the escape pod room to the south. (go east / go west)""",
            "You're back in the hallway.");
    Room room3 = new Room("SECTOR 3 - THE DINING ROOM", """
    You are in a dining room. The place is completely empty - except for a plate with a fresh burger on it on the dining table. 
    There is a door to the south. (go south / go west)""",
            "You're back in the dining room.");
    Room room4 = new Room("SECTOR 4 - THE OFFICE ROOM", """
    As the door opens, you're met with a sight you can't understand. 
    A huge mass takes up most of the room space. Massive tentacles surrounding it move idly. It looks asleep. 
    In the far corner, you see your old desk. You know the password for the escape pods might be in the top drawer.""",
            "You're back in the office room. The mass is not moving.");
    Room room5 = new Room("SECTOR 5 - THE ESCAPE POD", """
            As you run inside the room, the doors rapidly shut behind you.
            You run to the console and with shaking hands type in your destination.
            You sit down on the seat, tighten the seatbelt, and feel a sudden bump as the pod detaches itself.
            The door that barely held out the aliens slowly opens, and you see tentacles poke through.
            You made it.""",
            "You're back in the escape pod.");

    Room room6 = new Room("SECTOR 6 - THE HALLWAY WITH WINDOWS", """
    Huge windows cover the eastern side of the hallway walls. You look out at the vast space, sprinkled with stars and planets you have never seen before. 
    A sudden knock on the window draws your attention. 
    You see a person in a spacesuit floating in the dark. There's a huge hole in the helmet, with red liquid around the broken glass""",
            "You're back in the hallway.");

    Room room7 = new Room("SECTOR 7 - THE CHANGING ROOM", """
    A thick steam rolls out the hallway, as you enter. Every shower is running. It's hard to hear anything but the dripping water. 
    You look down and see red liquid flush down the drain.""",
            "You're back in the steam-filled hallway. And something is moving slowly, intentionally, in the steam.");

    Room room8 = new Room("SECTOR 8 - THE MONITORING ROOM", """
    There are strange machines, with surveillance video on seven of the nine screens, temperature and heart rate monitoring.
    By the door facing north, you see a terminal under the "ESCAPE POD" room sign. The screen flickers, but reads: "ENTER PASSWORD" """,
            "You're back in the monitoring room.");

    Room room9 = new Room("SECTOR 9 - THE LABORATORY", """
    An immediate horrible smell fills the room, as the door opens. You see a familiar face. But it's not where it belongs. 
    Stuck on the walls, you see several people you used to remember. 
    The medic, the mechanic, and then you see the face you hoped not to see - your wife's. 
    They are covered in organic matter. Their bellies are hanging out, and look extremely big. Something is moving inside of them.""",
            "You're back in the laboratory.");

    Weapon[] weapons = {
            new MeleeWeapon("wrench", "old, rusty wrench", 20),
            new RangedWeapon("laser rifle", "stasis laser rifle", 30, 5),
            new MeleeWeapon("tentacle", "sharp tentacle", 20)
    };

    Enemy[] enemies = {
            new Enemy("alien", "asdf", "description", 50, weapons[2], room1),
            new Enemy("alienTwo", "asdfTwo", "descriptionTwo", 50, weapons[2], room1)
    };

     public void buildItems(){
         room1.addItem("key card", "bloodied key card", "a");

         room2.addItem("flash light", "robust flash light", "a");

         room1.addItem(weapons[0]);
         room2.addItem(weapons[1]);

         room3.addItem("burger", "a fresh burger", 10);
         room3.addItem("battery", "smart space rocket laser battery", -99);
         room3.addItem("cola", "fresh, ice-cold bottle of cola", "a");

         room9.addItem("stim pack", "a stim pack for medical emergencies", 20);
     }

    public Room getStartRoom() {
        return room1;
    }

    public void buildMap() {
        room1.setEast(room2);
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
        room8.setEast(room9);
        room8.setNorth(room5);

        room9.setWest(room8);
        room9.setNorth(room6);

        // Låste rum
        room2.setRequiredItem("key card");
        room5.setPassword("42");
    }

    public void buildEnemies() {
        room6.addEnemy(enemies[0]);
        room6.addEnemy(enemies[1]);
    }

    public void buildDialogue() {
        // BUILD TERMINAL
        DialogueNode start = new DialogueNode("TERMINAL:", "Welcome, operator. How can I assist?");
        DialogueNode shipInfo = new DialogueNode("TERMINAL:", "The Hermes II is 112 years into its [REDACTED] voyage. \nSent by HomeStead Inc in search for [REDACTED] deep space specimens, artifacts, and highly [REDACTED] organic materials.");
        DialogueNode warning = new DialogueNode("TERMINAL:", "***WARNING*** Sensors detect another life signature onboard.");
        DialogueNode crewStatus = new DialogueNode("TERMINAL:", "Error: Unable to read bio-metrics of 10 out of 11 employees. Please consult an engineer for error finding.");
        DialogueNode shipStatus = new DialogueNode("TERMINAL:", "What would you like to check?");
        DialogueNode checkLifeSupport = new DialogueNode("TERMINAL: [LIFE SUPPORT]", """
        
        Oxygen consumption exceeds crew requirements by 31%.
        """);
        DialogueNode logEntries = new DialogueNode("TERMINAL: ", "Read log entry");
        DialogueNode logEntryHelios = new DialogueNode("AUDIO LOG - DRILLING STATION HELIOS", """
        \"I’m recording this because I need to know if I’m going insane, or if I’m just bored.
        
        After two weeks of drilling into the ice, we finally reached what we hoped to find: water on Europa-442.
        Is that really why they sent us here? For some [CENSORED] water? Don’t we have enough of that back home?
        
        My mind is… a bit all over the place right now. I’ve been running tests on water samples. 
        At first it didn’t look that different from our water back home. But then I saw something.
        
        I cut myself on a scalpel, and I could have sworn that I saw the water move towards the drop of blood next to it.
        [END OF LOG]\"""");
        DialogueNode logEntryLabResults = new DialogueNode("AUDIO LOG - LAB RESULTS", "Well..");
        DialogueNode engineStatus = new DialogueNode("TERMINAL:", "ENGINE STATUS: Fully functioning - Engine at full capacity.");
        DialogueNode systemsCheck = new DialogueNode("TERMINAL: ", """
        
        
        \033[1;97mSCANNERS:
        \033[0;97mCabin pressure:       \033[0;92m100%
        \033[0;97mGravity:              \033[0;92m100%
        \033[0;97mWater:                \033[0;92m87%
        \033[0;97mTemperature:          \033[0;92m93%
        \033[0;97mBio-metrics:          \033[1;91m1/11 (WARNING: Unable to scan bio-metrics of 10 out of 11 employees) 
        \033[0;97mOxygen levels:        \033[1;91m131% (WARNING: Oxygen consumption exceeds crew requirements by 31%)\u001b[0m
        """);

        // Start options
        start.addOption("Tell me about the ship.", shipInfo);
        start.addOption("Run systems check", systemsCheck);
        start.addOption("Read log entries", logEntries);
        start.addEndOption("Log off.");

        logEntries.addOption("AUDIO LOG - STATION HELIOS", logEntryHelios);
        logEntries.addOption("LOG - LAB RESULTS", logEntryLabResults);

        logEntryHelios.addOption("Go back", start);
        logEntryHelios.addEndOption("Log off.");

        shipInfo.addOption("Go back.", start);
        shipInfo.addEndOption("Log off.");

        systemsCheck.addOption("Go back", start);


        shipStatus.addOption("Check engine status.", engineStatus);
        shipStatus.addOption("Run systems check", systemsCheck);
        shipStatus.addOption("Check crew status.", crewStatus);
        shipStatus.addOption("Check Life Support", checkLifeSupport);

        checkLifeSupport.addOption("Go back", start);
        checkLifeSupport.addEndOption("Log off");


        engineStatus.addOption("Go back", shipStatus);
        engineStatus.addEndOption("Log off.");

        warning.addOption("Go back.", start);
        warning.addEndOption("Log off.");

        crewStatus.addOption("Go back", start);
        crewStatus.addEndOption("Log off.");

        room1.setTerminalDialogue(start);
    }

    public void buildWorld() {
         buildMap();
         buildEnemies();
         buildItems();
         buildDialogue();
    }
}
