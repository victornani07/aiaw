package agent;

public interface Agent {

    void see(Percept p);

    Action selectAction();

    String getId();

}
