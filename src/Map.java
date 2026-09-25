public class Map {

    Room room1 = new Room("ROOM 1 - THE SLEEPING PODS", """
            You are in a sleeping pod chamber. There are five more sleeping pods, but all of them are empty. 
            There are two doors, one facing east, the other facing south.
            """,
            "You're back in the sleeping pod chamber");
    Room room2 = new Room("ROOM 2 - THE HALLWAY", """
            A loud alarm is blaring through the speakers. The hallway is dark, the only thing lighting it up is the waves of red light from the alarm. 
            You see a door at the end of the hallway closing, and a dark shadow run through it. 
            A window is on your right that shows the escape pod room to the south. (go east / go west)""",
            "You're back in the hallway.");
    Room room3 = new Room("ROOM 3 - THE DINING ROOM", """
    You are in a dining room. The place is completely empty - except for a plate with a fresh burger on it on the dining table. 
    There is a door to the south. (go south / go west)""",
            "You hear a strange clicking sound as the doors open. The plate on the dining table is now empty and rattling fast, like someone had to touched it.");
    Room room4 = new Room("ROOM 4 - THE HIVE", """
    As the door opens, you're met with a sight you can't understand. 
    A huge mass takes up most of the room. The tentacles surrounding it move idly. It looks asleep. 
    In the far corner, you see your old desk. You know the key to room 5 might be in the top drawer.""",
            "");
    Room room5 = new Room("ROOM 5 - THE ESCAPE POD", """
            As you run inside the room, the doors rapidly shut behind you.
            You run to the console and with shaking hands type in your destination.
            You sit down on the seat, tighten the seatbelt, and feel a sudden bump as the pod detaches itself.
            The door that barely held out the aliens slowly opens, and you see tentacles poke through.
            You made it.""",
            "Room 5 short description");
    Room room6 = new Room("ROOM 6 - THE HALLWAY WITH WINDOWS", """
    Huge windows cover the eastern side of the hallway walls. You look out at the vast space, sprinkled with stars and planets you have never seen before. 
    A sudden knock on the window draws your attention. 
    You see a person in a spacesuit floating in the dark. There's a huge hole in the helmet, with red liquid around the broken glass""",
            "Room 6 short description");
    Room room7 = new Room("ROOM 7 - THE CHANGING ROOM", """
    A thick steam rolls out the room, as you enter. Every shower is running. It's hard to hear anything but the dripping water. 
    You look down and see red liquid flush down the drain.""",
            "Room 7 short description");
    Room room8 = new Room("ROOM 8 - THE MONITORING ROOM", """
    There are strange machines, with surveillance video on seven of the nine screens, temperature and heart rate monitoring.""",
            "Room 8 short description");
    Room room9 = new Room("ROOM 9 - THE LABORATORY", """
    An immediate horrible smell fills the room, as the door opens. You see a familiar face. But it's not where it belongs. 
    Stuck on the walls you see several people you used to remember. 
    The medic, the mechanic, and then you see the face you hoped not to see - your wife's. They are covered in organic matter. Their bellies are hanging out, and look extremely big. Something is moving inside of them.""",
            "Room 9 short description");

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
        room8.setNorth(room5);
        room8.setEast(room9);

        room9.setWest(room8);
        room9.setNorth(room6);
    }
}
