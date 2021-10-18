package vacworld;

import agent.Action;
import agent.Agent;
import agent.State;

public class RotateLeft implements Action {

    @Override
    public State execute(Agent a, State s) {
        char currentDir;

        if(!(s instanceof VacuumState))
            return null;

        VacuumState vs = (VacuumState) s;

        currentDir = vs.getDir();

        if(currentDir == 'N')
            currentDir = 'E';
        else if(currentDir == 'E')
            currentDir = 'S';
        else if(currentDir == 'S')
            currentDir = 'V';
        else
            currentDir = 'N';

        ((VacuumState) s).updateDir(currentDir);

        System.out.println("Rotating Left");
        return s;
    }
}
