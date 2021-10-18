package vacworld;

import agent.Percept;
import agent.State;

public class VacuumPercept implements Percept {
    private boolean dirt;
    private boolean obstacle;
    private VacuumState state;

    public VacuumPercept(State state) {
        dirt = false;
        obstacle = false;
        if(state instanceof VacuumState)
            this.state = (VacuumState)state;
    }

    @Override
    public void perceive() {

        int x = state.getX();
        int y = state.getY();

        if(state.hasDirt(x, y))
            dirt = true;

        if(state.hasObstacle(x, y))
            obstacle = true;
    }

    public boolean getDirt() {
        return dirt;
    }

    public boolean getObstacle() {
        return obstacle;
    }

    public void reset() {
        dirt = false;
        obstacle = false;
    }
}
