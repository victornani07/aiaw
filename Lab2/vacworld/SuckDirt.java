package vacworld;

import agent.State;

public class SuckDirt {

    public void execute(State s) {
        if(!(s instanceof VacuumState))
            return;

        ((VacuumState) s).cleanDirt();
    }
}
