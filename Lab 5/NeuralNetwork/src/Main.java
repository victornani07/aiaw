public class Main {
    public static void main(String[] args) {
        int[] s = new int[]{1, 0, 0, 0, 0, 0, 0, 1};

        Agent agent = new Agent(s);

        agent.displayConditions();
        agent.decide();
    }
}
