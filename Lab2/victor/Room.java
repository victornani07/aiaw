package victor;

import agent.Agent;
import agent.State;
import vacworld.VacuumState;

public class Room {
    public static void main(String[] args) {
        Agent agent = new VacuumCleaner();
        State state = new VacuumState();
        RoomCleaner cleaner = new RoomCleaner(agent, state);

        cleaner.clean();
    }
}
