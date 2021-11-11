// Clasa responsabila de crearea lumii Wampus
// As fi putut s-o creez in Main, dar am decis sa scriu o clasa special destinata
// Avem metoda addRoom, ce adauga camere
// si createMaze, care adauga camere cu elemente decise de mine

public class MazeCreator {
    private final Room[][] maze;
    private int roomsAdded;

    public MazeCreator() {
        maze = new Room[4][4];
        roomsAdded = 0;
    }

    private void addRoom(int i, int j, int aspect) {
        if(roomsAdded == 16)
            return;

        if(maze[i][j] == null) {
            maze[i][j] = new Room();
            ++roomsAdded;
        }

        maze[i][j].addAspect(aspect);
    }

    private void createMaze() {
        addRoom(0, 0, Aspects.STENCH);
        addRoom(0, 1, Aspects.CLEAR);
        addRoom(0, 2, Aspects.BREEZE);
        addRoom(0, 3, Aspects.PIT);

        addRoom(1, 0, Aspects.WUMPUS);
        addRoom(1, 1, Aspects.GOLD);
        addRoom(1, 1, Aspects.STENCH);
        addRoom(1, 1, Aspects.BREEZE);
        addRoom(1, 2, Aspects.PIT);
        addRoom(1, 3, Aspects.BREEZE);

        addRoom(2, 0, Aspects.STENCH);
        addRoom(2, 1, Aspects.CLEAR);
        addRoom(2, 2, Aspects.BREEZE);
        addRoom(2, 3, Aspects.CLEAR);

        addRoom(3, 0, Aspects.CLEAR);
        addRoom(3, 1, Aspects.BREEZE);
        addRoom(3, 2, Aspects.PIT);
        addRoom(3, 3, Aspects.BREEZE);
    }

    public Room[][] getMaze() {
        createMaze();

        return maze;
    }

}
