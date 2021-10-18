package agent;

public interface Action {

    State execute(Agent a, State s);
}
