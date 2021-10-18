package vacworld;

import agent.Action;
import agent.Agent;
import agent.State;

public class RotateRight implements Action {

    @Override
    public State execute(Agent a, State s) {
        char currentDir;

        if(!(s instanceof VacuumState))
            return null;

        VacuumState vs = (VacuumState) s;

        currentDir = vs.getDir();

        if(currentDir == 'N')
            currentDir = 'V';
        else if(currentDir == 'V')
            currentDir = 'S';
        else if(currentDir == 'S')
            currentDir = 'E';
        else
            currentDir = 'N';

        ((VacuumState) s).updateDir(currentDir);
        System.out.println("Rotating Right");
        return s;
    }
}
