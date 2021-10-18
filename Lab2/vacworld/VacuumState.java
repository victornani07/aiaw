package vacworld;

import agent.State;

public class VacuumState implements State {
    public static final int CLEAR = 0;
    public static final int DIRT = 1;
    public static final int WALL = 2;

    private int[][] room = {
                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                    {WALL, CLEAR, CLEAR, CLEAR, WALL, WALL, WALL},
                    {WALL, CLEAR, DIRT, WALL, CLEAR, WALL, WALL},
                    {WALL, CLEAR, CLEAR, CLEAR, DIRT, CLEAR, WALL},
                    {WALL, CLEAR, DIRT, CLEAR, CLEAR, DIRT, WALL},
                    {WALL, WALL, WALL, WALL, CLEAR, CLEAR, WALL},
                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL}
    };

    private int x;
    private int y;
    private char dir;

    public VacuumState() {
        x = 3;
        y = 3;
        dir = 'N';
    }

    @Override
    public void display() {
        for(int i = 0; i < room.length; ++i) {
            for(int j = 0; j < room[0].length; ++j)
                if(i == x && j == y) {
                    if(dir == 'N')
                        System.out.print("A ");
                    else if(dir == 'S')
                        System.out.print("V ");
                    else if(dir == 'E')
                        System.out.print("< ");
                    else
                        System.out.print("> ");
                } else {
                    if(room[i][j] == WALL)
                        System.out.print("X ");
                    else if(room[i][j] == CLEAR)
                        System.out.print("0 ");
                    else
                        System.out.print("* ");
                }

            System.out.println();
        }

        System.out.println();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDir() {
        return dir;
    }

   public void updateX(int x) {
        this.x = x;
   }

   public void updateY(int y) {
        this.y = y;
   }

   public void updateDir(char dir) {
        this.dir = dir;
   }

   public boolean hasObstacle(int x, int y) {
        return room[x][y] == WALL;
   }

   public boolean hasDirt(int x, int y) {
        return room[x][y] == DIRT;
   }

   public void cleanDirt() {
        room[x][y] = CLEAR;
   }

   public int countDirtyLocations() {
        int count = 0;

       for (int[] ints : room)
           for (int place : ints)
               if (place == DIRT)
                   ++count;

        return count;
   }
}
