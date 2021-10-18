package victor;

public class Position {
    private final int x;
    private final int y;
    private final char dir;

    public Position(int x, int y, char dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public char getDir() {
        return this.dir;
    }
}
