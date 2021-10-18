package victor;

import agent.Action;
import agent.Agent;
import agent.Percept;
import vacworld.*;

public class VacuumCleaner implements Agent {
    private final Memory memory;
    private final int properId;
    private static int id = 0;
    private int actionCount;

    public VacuumCleaner() {
        actionCount = 0;
        memory = new Memory();
        memory.add(3, 3, 'N');
        properId = id++;
    }

    @Override
    public void see(Percept percept) {
        if(!(percept instanceof VacuumPercept))
            return;

        percept.perceive();
    }

    @Override
    public Action selectAction() {
        Action action;

        if(actionCount == 0)
            action = new MoveForward();
        else if(actionCount == 1)
            action = new RotateLeft();
        else if(actionCount == 2)
            action = new RotateRight();
        else
            action = new MoveBackward();

        return action;
    }

    @Override
    public String getId() {
        return "Agent " + properId;
    }

    public void updateMemory(int x, int y, char dir) {
        memory.add(x, y, dir);
    }

    public boolean checkMemory(int x, int y, char dir) {
        Position position = new Position(x, y, dir);
        return memory.contains(position);
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }
}
