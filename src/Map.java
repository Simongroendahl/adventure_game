public class Map {

    Room room1 = new Room("==== SECTOR 1: THE SLEEPING PODS ====", """
            
    \033[0;97mYou awake. You're in a sleeping pod chamber. There are ten more sleeping pods - all are open, all are empty. 
    You step out, and feel a sharp pain under your foot. Broken glass. You're bleeding, but you don't think much of it.
    There are two doors: the one facing [south] is bulging inwards, as if something big ran into it from the other room.
    The sign next to it glows red, displaying: [MALFUNCTION]. The door to the [east] has a key card terminal.\u001b[0m""",
            "\033[0;97m\nYou're back in the sleeping pod chamber\u001b[0m", null);

    Room room2 = new Room("==== SECTOR 2: THE HALLWAY ====", """
            
    \033[1;92m*** ACCESS GRANTED ***\u001b[0m
    \033[0;97mA loud alarm is blaring through the speakers. The hallway is only briefly lit up by the pulsating red color from the alarm. 
    You can vaguely make out some smeared writing on the wall. "THEY ARE EVERYWHERE. THEY ARE US."
    Then your eye catches something. A shape. Shapes. Something scurrying rapidly across the floor, disappearing behind the closing door down the hallway.
    Not far from the door, you find the author of the smeared writing. Or whatever is left of them. 
    It's hard to look away, but you're distracted by what his hand, across the room from him, is still holding on to.\u001b[0m""",
            "\033[0;97m\nYou're back in the hallway.\u001b[0m", Sound.ALARM_SIREN);

    /*Room room3 = new Room("==== SECTOR 3: THE DINING ROOM ====", """
    
    \033[0;97mYou are in a dining room. The place is completely empty - except for a plate with a fresh burger on it on the dining table. 
    There is a door to the south. (go south / go west)\u001b[0m""",
            "\033[0;97m\nYou're back in the dining room.\u001b[0m", null);*/

    Room room3 = new Room("==== SECTOR 3: THE RESEARCH LAB ====", """
    
    \033[0;97mFor the first time, since you woke up you're met with a familiar view. The always messy lab, that must be physically impossible to keep tidy.
    But it's not messy in the usual way. The glass from a big containment unit you've never seen before lays scattered across the floor.
    You hear beeping from the monitor on the desk to your right. You might want to check out the [terminal].\u001b[0m""",
            "\033[0;97m\nYou're back in the research lab.\u001b[0m", Sound.RESEARCH_LAB);

    Room room4 = new Room("==== SECTOR 4: THE ENGINE ROOM ====", """
    
    \033[0;97mThe engine room is quiet except for the low hum of the reactor. Then you see the crew.
    They are all here, fused to the walls in dark, fibrous growths, their bodies hanging loose beneath swollen stomachs. 
    Something moves inside one of them, slowly pushing against the stretched skin. Then another moves. And another.
    
    Beside the reactor, a terminal is still glowing: ***ESCAPE POD ACCESS CODE: 42***\u001b[0m""",
            "\033[0;97m\nYou're back in the engine room.\u001b[0m", null);

    Room room5 = new Room("==== SECTOR 5: THE ESCAPE POD ====", """
    
    \033[0;97mAs you run inside the room, the doors rapidly shut behind you.
    You run to the console and with shaking hands type in your destination.
    You sit down on the seat, tighten the seatbelt, and feel a sudden bump as the pod detaches itself.
    The door that barely held out the aliens slowly opens, and you see tentacles poke through.
    
    You made it.\u001b[0m""",
            "\033[0;97m\nYou're back in the escape pod.\u001b[0m", null);

    Room room6 = new Room("==== SECTOR 6: THE WINDOW ROOM ====", """
    \033[0;97mHuge windows cover the entire eastern side of the room. You look out at the vast space. 
    Stars and planets and galaxies sprinkled the hungry, black void looking at you. 
    A sudden knock on the glass draws your attention. Seated on the ground, a person in a spacesuit is banging their head idly against the glass.
    There's a huge hole in the helmet, with red liquid around the broken glass. 
    
    Three creatures crawl out of the helmet. Small, pale, celapholod-like organisms, roughly 40 cm long. 
    They scurry across the floor towards you, leaving red trails behind them. Then they pause in their tracks, looking at you with no apparent eyes.\u001b[0m""",
            "\033[0;97m\nYou're back in the window room.\u001b[0m", null);

    Room room7 = new Room("==== SECTOR 7: THE CARGO BAY ====", """
    \033[0;97mThe cargo bay is vast and poorly lit. Rows of steel containers are stacked along the walls, most marked with faded mission numbers.
    At the center of the bay stands a tall, black monolith.
    Its surface is perfectly smooth, yet covered in shallow markings that resemble ancient human writing. No known language matches them.
    The material is unlike anything in the ship's database. It is cold to the touch, but the scanner reports no measurable temperature.
    You have no memory of seeing it in the mission inventory.\u001b[0m""",
            "\033[0;97m\nYou're back in the cargo bay.\u001b[0m", null);

    Room room8 = new Room("==== SECTOR 8: THE AIRLOCK ====", """
    \033[0;97mThe airlock is cold and sterile. Heavy doors seal the chamber from either side, surrounded by decontamination equipment and emergency controls.
    The system appears to be offline. To the north, a sealed door leads back toward the escape pod chamber. A small display beside it is still powered.
            
    The screen reads: [ENTER PASSWORD:]
            
    A thin layer of frost covers the floor beneath the inner door.\u001b[0m""",
            "\033[0;97m\nYou're back in the airlock.\u001b[0m", null);

    Room room9 = new Room("==== SECTOR 9: CREW QUARTERS ====", """
    
    \033[0;97mAThe crew quarters are smaller than you expected. 
    Six rooms, each still carrying the small evidence of lives interrupted: a book left open, clothes folded beside a bed, a photograph taped to a wall.
    Someone has left a half-finished chess game on a table. The pieces are still where they were played. 
    There are no signs of a struggle here. For a moment, it feels less like a dead ship and more like everyone simply stepped out.\u001b[0m""",
            "\033[0;97m\nYou're back in the crew quarters.\u001b[0m", null);

    Weapon[] weapons = {
            new MeleeWeapon("wrench", "old, rusty [wrench]", 20),
            new RangedWeapon("laser rifle", "stasis [laser rifle]", 30, 5),
            new MeleeWeapon("claw", "sharp metal-like claw", 30),
            new MeleeWeapon("beak", "sharp beak", 5)
    };

    Enemy[] enemies = {
            // MAIN MONSTER
            new Enemy("xenoform", "xenoform", """
            Something large moves in the darkness. 
            It emerges slowly, deliberately. It is enormous, all muscle, claws, teeth, and darkened flesh stretched tight over its frame, with limbs that bend at the wrong places.
            Its head tilts to the side, observing you - just the way it has since before you woke up. There is no hatred where its eyes should be. No recognition. Only hunger.
            You understand, with sudden certainty, that it isn't deciding whether to kill you.
            It's deciding when.""", 100, weapons[2], room7),

            // ENEMY CRITTERS
            new Enemy("cephalopod 1", "long desc tiny squid", "", 20, weapons[3], room6),
            new Enemy("cephalopod 2", "long desc tiny squid", "", 20, weapons[3], room6),
            new Enemy("cephalopod 3", "long desc tiny squid", "", 20, weapons[3], room6),
    };

     public void buildItems(){
         //SECTOR 1: The Sleeping Pods
         room1.addItem("key card", "bloodied [key card]", "a");
         room1.addItem(weapons[0]);

         // SECTOR 2: The Hallway
         room2.addItem("flash light", "robust [flash light]", "a");
         room2.addItem(weapons[1]); //LASER RIFLE

         // SECTOR 3: The Research Lab
         room3.addItem("battery", "fusion battery", -99);
         room3.addItem("cola", "fresh, ice-cold bottle of Homestead cola", 40);

         // SECTOR 4: The Engine Room

         // SECTOR 5: The Escape Pods

         // SECTOR 6: The Window Room
         room6.addItem("stim pack", "military-grade [stim pack] (healing)", 40);

         // SECTOR 7: The Cargo Bay

         // SECTOR 8: The Airlock

         // SECTOR 9: Crew Quarters
         room9.addItem("burger", "fresh [burger]", 10);

         room9.addItem("stim pack", "[stim pack] for medical emergencies", 20);
     }

    public Room getStartRoom() {
        return room1;
    }

    public void buildMap() {
        room1.setEast(room2);
        /*room1.setSouth(room4);*/

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

        // Squid critters
        room6.addEnemy(enemies[1]);
        room6.addEnemy(enemies[2]);
        room6.addEnemy(enemies[3]);
    }

    public void buildDialogue() {
        // BUILD TERMINAL
        DialogueNode start = new DialogueNode("==== SHIP SYSTEMS TERMINAL ====", "");
        DialogueNode shipInfo = new DialogueNode("==== TERMINAL: SHIP STATUS OVERVIEW ====", """
        
        
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


        DialogueNode logEntries = new DialogueNode("==== TERMINAL: LOG DATABASE ====", "");

        DialogueNode logEntryHelios = new DialogueNode("==== TERMINAL: DRILLING STATION HELIOS ====", """
        
        \033[1;97mLOG DATABASE:
        \"I’m recording this because I need to know if I’m going insane, or if I’m just bored.
        
        After two weeks of drilling into the ice, we finally reached what we hoped to find: water on Europa-442.
        Is that really why they sent us here? For some [CENSORED] water? Don’t we have enough of that back home?
        
        My mind is… a bit all over the place right now. I’ve been running tests on water samples. 
        At first it didn’t look that different from our water back home. But then I saw something.
        
        I cut myself on a scalpel, and I could have sworn that I saw the water move towards the drop of blood next to it.
        [END OF LOG]\"""");

        DialogueNode logLabResults = new DialogueNode("==== TERMINAL: LABORATORY DATABASE ====", "");

        DialogueNode labResultsIC04 = new DialogueNode("==== TERMINAL: LABORATORY DATABASE ====", """
        
        
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

        DialogueNode labResultsOS02 = new DialogueNode("==== TERMINAL: LABORATORY DATABASE ====", """
        
        
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

        DialogueNode systemsCheck = new DialogueNode("==== TERMINAL: SYSTEM DIAGNOSTICS ====", """
        
        
        \033[1;97mSCANNERS:
        \033[0;97m---------------------------------------
        \033[0;97mCabin pressure:       \033[0;92m100%
        \033[0;97mGravity:              \033[0;92m100%
        \033[0;97mWater:                \033[0;92m87%
        \033[0;97mTemperature:          \033[0;92m93%
        \033[0;97mBio-metrics:          \033[1;91m1/11 (WARNING: Unable to scan bio-metrics of 10 out of 11 employees) 
        \033[0;97mOxygen levels:        \033[1;91m131% (WARNING: Oxygen consumption exceeds crew requirements by 31%)\u001b[0m
        
        \033[0;97mUnregistered biological signature detected.\u001b[0m""");

        DialogueNode bioSignature = new DialogueNode("==== TERMINAL: SYSTEM DIAGNOSTICS ====", """
         
         
         \033[1;97mBIOLOGICAL SCAN:
         \033[0;97m--------------------
         \033[0;97mLocation:                       [UNABLE TO MEASURE]
         \033[0;97mHeart rate:                     00 BPM
         \033[0;97mBody temperature:               7.5 C
             
         \033[1;91mWARNING:                        \033[0;97mScanner accuracy might be affected to atmospheric interference.\u001b[0m""");

        // Start options
        start.addOption("[SHIP STATUS]", shipInfo);
        start.addOption("[RUN SYSTEM DIAGNOSTICS]", systemsCheck);
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

        room3.setTerminalDialogue(start);
    }

    public void buildWorld() {
         buildMap();
         buildEnemies();
         buildItems();
         buildDialogue();
    }
}
