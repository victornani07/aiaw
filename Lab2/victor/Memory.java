package victor;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private final List<Position> visited;

    public Memory() {
        visited = new ArrayList<>();
    }

    public void add(int x, int y, char dir) {
        Position position = new Position(x, y, dir);

        if(!contains(position))
            visited.add(position);
    }

    public boolean contains(Position position) {
        for(Position p : visited)
            if(p.getX() == position.getX() && p.getY() == position.getY() && p.getDir() == position.getDir())
                return true;

        return false;
    }
}
