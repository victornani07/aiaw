package victor;

import agent.Action;
import agent.Agent;
import agent.State;
import vacworld.VacuumState;

public class MoveBackward implements Action {

    @Override
    public State execute(Agent a, State s) {
        int currentX, currentY;
        char currentDir;

        if(!(s instanceof VacuumState))
            return null;

        if(!(a instanceof VacuumCleaner))
            return null;

        VacuumState vs = (VacuumState) s;

        currentX = vs.getX();
        currentY = vs.getY();
        currentDir = vs.getDir();

        if(currentDir == 'N' && currentX + 1 < 7)
            ++currentX;
        else if(currentDir == 'S' && currentX - 1 >= 0)
            --currentX;
        else if(currentDir == 'E' && currentY + 1 < 7)
            ++currentY;
        else if(currentDir == 'V' && currentY - 1 >= 0)
            --currentY;
        else {
            System.out.println("Can not move backward.");
            return null;
        }

        ((VacuumState) s).updateX(currentX);
        ((VacuumState) s).updateY(currentY);
        ((VacuumCleaner) a).updateMemory(currentX, currentY, currentDir);
        System.out.println("Moving Backward");
        return s;
    }
}
