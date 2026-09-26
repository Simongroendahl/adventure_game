public class Map {
private Room room1;
private Room room2;
private Room room3;
private Room room4;
private Room room5;
private Room room6;
private Room room7;
private Room room8;
private Room room9;
public Map () {
    room1 = new Room("Room 1", "You are in a sleeping pod chamber. There are five more sleeping pods, but all of them are empty. There are two doors, one facing east, the other facing south.", "You're back in the sleeping pod chamber");
    room2 = new Room("Room 2", "You are in long, dark hallway. Red flickering lights shower the room. An alarm has gone off. You see a door at the end of the hallway. (go east / go west)", "You're back in the hallway.");
    room3 = new Room("Room 3", "Asdf", "Asdf rum 3");
    room4 = new Room("Room 4", "The room is bla bla bla. There's a window with a full view of the sleeping room. There are strange machines, with surveillance videos on the screens, temperature and heart rate monitoring.", "");
    room5 = new Room("Room 5", "Room 5 is in space", "Room 5 short description");
    room6 = new Room("Room 6", "Room 6 is in space", "Room 6 short description");
    room7 = new Room("Room 7", "Room 7 is in space", "Room 7 short description");
    room8 = new Room("Room 8", "Room 8 is in space", "Room 8 short description");
    room9 = new Room("Room 9", "Room 9 is in space", "Room 9 short description");


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
        public Room getStartRoom(){
            return room1;

    }
    }

