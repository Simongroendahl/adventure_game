public class Map {

    Room room1 = new Room("SECTOR 1 - THE SLEEPING PODS", """
            
    \033[0;97mYou are in a sleeping pod chamber. There are ten more sleeping pods, but all of them are empty. 
    There are two doors: one facing east with a key card terminal; sparks are flying from the door facing south.\u001b[0m""",
            "\033[0;97m\nYou're back in the sleeping pod chamber\u001b[0m", null);

    Room room2 = new Room("SECTOR 2 - THE HALLWAY", """
            
    \033[1;92m*** ACCESS GRANTED ***\u001b[0m
    \033[0;97mA loud alarm is blaring through the speakers. The hallway is dark, only lit up by waves of red light from the alarm. 
    A dark shadow runs through the closing door, at the end of  the hallway. 
    A window is on your right that shows the escape pod room to the south. (go east / go west)\u001b[0m""",
            "\033[0;97m\nYou're back in the hallway.\u001b[0m", Sound.ALARM_SIREN);

    Room room3 = new Room("SECTOR 3 - THE DINING ROOM", """
    
    \033[0;97mYou are in a dining room. The place is completely empty - except for a plate with a fresh burger on it on the dining table. 
    There is a door to the south. (go south / go west)\u001b[0m""",
            "\033[0;97m\nYou're back in the dining room.\u001b[0m", null);

    Room room4 = new Room("SECTOR 4 - THE OFFICE ROOM", """
    
    \033[0;97mAs the door opens, you're met with a sight you can't understand. 
    A huge mass takes up most of the room space. Massive tentacles surrounding it move idly. It looks asleep. 
    In the far corner, you see your old desk. You know the password for the escape pods might be in the top drawer.\u001b[0m""",
            "\033[0;97m\nYou're back in the office room. The mass is not moving.\u001b[0m", null);

    Room room5 = new Room("SECTOR 5 - THE ESCAPE POD", """
    
    \033[0;97mAs you run inside the room, the doors rapidly shut behind you.
    You run to the console and with shaking hands type in your destination.
    You sit down on the seat, tighten the seatbelt, and feel a sudden bump as the pod detaches itself.
    The door that barely held out the aliens slowly opens, and you see tentacles poke through.
    You made it.\u001b[0m""",
            "\033[0;97m\nYou're back in the escape pod.\u001b[0m", null);

    Room room6 = new Room("SECTOR 6 - THE HALLWAY WITH WINDOWS", """
    \033[0;97mHuge windows cover the eastern side of the hallway walls. You look out at the vast space, sprinkled with stars and planets you have never seen before. 
    A sudden knock on the window draws your attention. 
    You see a person in a spacesuit floating in the dark. There's a huge hole in the helmet, with red liquid around the broken glass.\u001b[0m""",
            "\033[0;97m\nYou're back in the hallway.\u001b[0m", null);

    Room room7 = new Room("SECTOR 7 - THE CHANGING ROOM", """
    \033[0;97mA thick steam rolls out the hallway, as you enter. Every shower is running. It's hard to hear anything but the dripping water. 
    You look down and see red liquid flush down the drain.\u001b[0m""",
            "\033[0;97m\nYou're back in the steam-filled hallway. And something is moving slowly, intentionally, in the steam.\u001b[0m", null);

    Room room8 = new Room("SECTOR 8 - THE MONITORING ROOM", """
    \033[0;97mThere are strange machines, with surveillance video on seven of the nine screens, temperature and heart rate monitoring.
    By the door facing north, you see a terminal under the "ESCAPE POD" room sign. The screen flickers, but reads: "ENTER PASSWORD"\u001b[0m""",
            "\033[0;97m\nYou're back in the monitoring room.\u001b[0m", null);

    Room room9 = new Room("SECTOR 9 - THE LABORATORY", """
    
    \033[0;97mAn immediate horrible smell fills the room, as the door opens. You see a familiar face. But it's not where it belongs. 
    Stuck on the walls, you see several people you used to remember. 
    The medic, the mechanic, and then you see the face you hoped not to see - your wife's. 
    They are covered in organic matter. Their bellies are hanging out, and look extremely big. Something is moving inside of them.\u001b[0m""",
            "\033[0;97m\nYou're back in the laboratory.\u001b[0m", null);

    Weapon[] weapons = {
            new MeleeWeapon("wrench", "old, rusty [wrench]", 20),
            new RangedWeapon("laser rifle", "stasis [laser rifle]", 30, 5),
            new MeleeWeapon("tentacle", "sharp tentacle", 20),
            new MeleeWeapon("beak", "sharp beak", 5)
    };

    Enemy[] enemies = {
            new Enemy("flying squid", "asdf", "description", 50, weapons[2], room7),
            new Enemy("flying squid", "asdfTwo", "descriptionTwo", 50, weapons[2], room7),

            // ENEMY CRITTERS
            new Enemy("tiny squid 1", "long desc tiny squid", "\033[0;97mThree, small squids claw their way out of the belly. They scurry around the floor, leaving red trails behind them.\nThen they pause and stare with no eyes. They've noticed you.\u001b[0m", 20, weapons[3], room6),
            new Enemy("tiny squid 2", "long desc tiny squid", "description", 20, weapons[3], room6),
            new Enemy("tiny squid 3", "long desc tiny squid", "description", 20, weapons[3], room6),
    };

     public void buildItems(){
         room1.addItem("key card", "bloodied [key card]", "a");
         room1.addItem(weapons[0]);
         room2.addItem(weapons[1]);

         room2.addItem("flash light", "robust [flash light]", "a");

         room3.addItem("burger", "a fresh [burger]", 10);
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
        room7.addEnemy(enemies[0]);
        room7.addEnemy(enemies[1]);

        // Squid critters
        room6.addEnemy(enemies[2]);
        room6.addEnemy(enemies[3]);
        room6.addEnemy(enemies[4]);
    }

    public void buildDialogue() {
        // BUILD TERMINAL
        DialogueNode start = new DialogueNode("==== SHIP SYSTEMS TERMINAL ====", "");
        DialogueNode shipInfo = new DialogueNode("==== SHIP STATUS OVERVIEW ====", """
        
        
        \033[1;97mSHIP STATUS:
        \033[0;97m----------------------------------------------------
        \033[0;97mVessel:                         HERMES II
        \033[0;97mClass:                          Deep Space Research
        \033[0;97mMission:                        [REDACTED]
        \033[0;97mLocation:                       [REDACTED]
        \033[0;97mMission day:                    40.880
        
        \033[0;97mHull integrity:                 97%
        \033[0;97mPower:                          83%
        \033[0;97mFuel:                           64%
        \033[0;97mCommunications:                 \033[1;91mOFFLINE\u001b[0m
        \033[0;97mCrew:                           12/11 (ERROR)\u001b[0m""");


        DialogueNode logEntries = new DialogueNode("==== LOG DATABASE ====", "");

        DialogueNode logEntryHelios = new DialogueNode("AUDIO LOG - DRILLING STATION HELIOS", """
        
        \033[1;97mLOG DATABASE:
        \"I’m recording this because I need to know if I’m going insane, or if I’m just bored.
        
        After two weeks of drilling into the ice, we finally reached what we hoped to find: water on Europa-442.
        Is that really why they sent us here? For some [CENSORED] water? Don’t we have enough of that back home?
        
        My mind is… a bit all over the place right now. I’ve been running tests on water samples. 
        At first it didn’t look that different from our water back home. But then I saw something.
        
        I cut myself on a scalpel, and I could have sworn that I saw the water move towards the drop of blood next to it.
        [END OF LOG]\"""");

        DialogueNode logLabResults = new DialogueNode("==== SST: LABORATORY DATABASE ====", "");

        DialogueNode labResultsIC04 = new DialogueNode("==== SHIP SYSTEMS TERMINAL ====", """
        
        
        \033[1;97mLAB RESULTS: ICE CORE SAMPLE 04
        \033[0;97mLOG 0047 || DATE: 3112-11-03 || AUTHOR: Dr. Lena Chen - Astrobiology
        \033[0;97m---------------------------------------------------------------------
                
        \033[0;97mSAMPLE:        IC-04
        \033[0;97mRESULT:        INCONCLUSIVE
        
        Ice composition is exactly what we'd expect. No microbial activity. No organic compounds. Nothing exciting.        
        There is, however, a thin layer running through the sample that shouldn't be there.
        It looks almost like a fracture, but the ice around it is completely undisturbed.
                
        I'll run another scan tomorrow.
                
        — L. Chen\u001b[0m""");

        DialogueNode labResultsOS02 = new DialogueNode("==== SHIP SYSTEMS TERMINAL====", """
        
        
        \033[1;97mLAB RESULTS: ORGANIC SAMPLE 04
        \033[0;97mLOG 0052 || DATE: 3112-11-06 || AUTHOR: Dr. Lena Chen - Astrobiology
        \033[0;97m----------------------------------------------------
                
        \033[0;97mSAMPLE:        OS-02
        \033[0;97mRESULT:        NO VIABLE ACTIVITY
                
        The material appears to be organic. We're estimating its age at roughly 10,000 years.  
        There are no living cells. No metabolic activity. Nothing that should be capable of movement.
        I say "should" because the sample was in a different position when I came back this morning.
        Probably contamination. I'll have Security check the camera footage.
                
        Note to self: stop working alone after midnight.
                
        — L. Chen\u001b[0m""");

        DialogueNode systemsCheck = new DialogueNode("==== SYSTEM DIAGNOSTICS ====", """
        
        
        \033[1;97mSCANNERS:
        \033[0;97m---------------------------------------
        \033[0;97mCabin pressure:       \033[0;92m100%
        \033[0;97mGravity:              \033[0;92m100%
        \033[0;97mWater:                \033[0;92m87%
        \033[0;97mTemperature:          \033[0;92m93%
        \033[0;97mBio-metrics:          \033[1;91m1/11 (WARNING: Unable to scan bio-metrics of 10 out of 11 employees) 
        \033[0;97mOxygen levels:        \033[1;91m131% (WARNING: Oxygen consumption exceeds crew requirements by 31%)\u001b[0m
        
        \033[0;97mUnregistered biological signature detected.\u001b[0m""");

        DialogueNode bioSignature = new DialogueNode("==== SYSTEM DIAGNOSTICS ====", """
         
         
         \033[1;97mBIOLOGICAL SCAN:
         \033[0;97m--------------------
         \033[0;97mLocation:                       [UNABLE TO MEASURE]
         \033[0;97mHeart rate:                     00 BPM
         \033[0;97mBody temperature:               7.5 C
             
         \033[1;91mWARNING:                        \033[0;97mScanner accuracy might be affected to atmospheric interference.\u001b[0m""");

        // Start options
        start.addOption("[SHIP STATUS]", shipInfo);
        start.addOption("[SYSTEM DIAGNOSTICS]", systemsCheck);
        start.addOption("[LOG DATABASE]", logEntries);
        start.addEndOption("[Log off]");

        logEntries.addOption("[STATION HELIOS]", logEntryHelios);
        logEntries.addOption("[LAB RESULTS]", logLabResults);
        logEntries.addOption("[Go back]", start);

        logLabResults.addOption("[Ice Core Sample 04]", labResultsIC04);
        logLabResults.addOption("[Organic Sample 02]", labResultsOS02);
        logLabResults.addOption("[Go back]", start);
        logLabResults.addEndOption("[Log off]");

        labResultsIC04.addOption("[Read log: Organic Sample 02]", labResultsOS02);
        labResultsIC04.addOption("[Go back]", logEntries);

        labResultsOS02.addOption("[Read log: Ice Core Sample 04]", labResultsIC04);
        labResultsOS02.addOption("[Go back]", logEntries);

        logEntryHelios.addOption("[Go back]", start);
        logEntryHelios.addEndOption("[Log off]");

        shipInfo.addOption("[Go back]", start);
        shipInfo.addEndOption("[Log off]");

        systemsCheck.addOption("[Scan unregistered detection]", bioSignature);
        systemsCheck.addOption("[Go back]", start);

        bioSignature.addOption("[Go back]", start);

        room1.setTerminalDialogue(start);
    }

    public void buildWorld() {
         buildMap();
         buildEnemies();
         buildItems();
         buildDialogue();
    }
}
