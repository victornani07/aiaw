package victor;

import agent.Action;
import agent.Agent;
import agent.Percept;
import agent.State;
import vacworld.*;

public class RoomCleaner {
    private final Agent agent;
    private State state;
    private final Percept percept;
    private final Performance performance;

    public RoomCleaner(Agent agent, State state) {
        this.agent = agent;
        this.state = state;
        percept = new VacuumPercept(state);
        performance = new Performance(200);
    }

    public void clean() {
        if(!(agent instanceof VacuumCleaner))
            return;

        if(!(state instanceof VacuumState))
            return;

        if(!(percept instanceof VacuumPercept))
            return;

        System.out.println("Initial state " + performance.getStates());
        performance.setStates(performance.getStates() + 1);
        System.out.println("----------------");
        state.display();

        int dirtCleaned = 0, dirtyLocations = ((VacuumState) state).countDirtyLocations();

        ((VacuumCleaner) agent).setActionCount(0);
        Action moveForward = agent.selectAction();

        ((VacuumCleaner) agent).setActionCount(3);
        Action moveBackward = agent.selectAction();

        ((VacuumCleaner) agent).setActionCount(1);
        Action turnLeft = agent.selectAction();

        while(dirtCleaned != dirtyLocations) {

            System.out.println("State " + performance.getStates());
            performance.setStates(performance.getStates() + 1);
            System.out.println("----------------");
            ((VacuumPercept) percept).reset();
            percept.perceive();
            boolean dirt = ((VacuumPercept) percept).getDirt();
            boolean obstacle = ((VacuumPercept) percept).getObstacle();

            if(dirt) {
                SuckDirt suckDirt = new SuckDirt();
                suckDirt.execute(state);
                ++dirtCleaned;
            }

            if(obstacle) {
                state = moveBackward.execute(agent, state);
                performance.setMoves(performance.getMoves() + 1);
                int x = ((VacuumState) state).getX();
                int y = ((VacuumState) state).getY();
                char dir = ((VacuumState) state).getDir();
                int j = 0;

                while (j <= 4) {
                    if (((VacuumCleaner) agent).checkMemory(x, y, dir)) {
                        state = turnLeft.execute(agent, state);
                        performance.setMoves(performance.getMoves() + 1);
                        dir = ((VacuumState) state).getDir();
                        ++j;
                    } else {
                        ((VacuumCleaner) agent).updateMemory(x, y, dir);
                        break;
                    }

                    if (j == 4) {
                        j = 0;
                        state = moveBackward.execute(agent, state);
                        performance.setMoves(performance.getMoves() + 1);
                        x = ((VacuumState) state).getX();
                        y = ((VacuumState) state).getY();
                        dir = ((VacuumState) state).getDir();
                    }
                }
            }

            state = moveForward.execute(agent, state);
            performance.setMoves(performance.getMoves() + 1);

            int x = ((VacuumState) state).getX();
            int y = ((VacuumState) state).getY();
            char dir = ((VacuumState) state).getDir();

            ((VacuumCleaner) agent).updateMemory(x, y, dir);
            System.out.println("----------------");
            System.out.println("DIRT - " + dirt);
            System.out.println("OBSTACLE - " + obstacle);
            System.out.println("----------------");
            state.display();
        }

        System.out.println("Final state " + performance.getStates());
        System.out.println("----------------");
        state.display();

        TurnOff turnOff = new TurnOff();
        turnOff.turnOff();

        System.out.println();
        performance.showResults();
    }
}
