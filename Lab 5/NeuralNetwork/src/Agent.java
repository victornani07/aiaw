public class Agent {
    private final NeuralNetwork neuralNetwork;
    private int[] sensorySignals;

    public Agent(int[] sensorySignals) {
        this.sensorySignals = sensorySignals;
        neuralNetwork = new NeuralNetwork(sensorySignals);
    }

    public void displayConditions() {
        System.out.println("For the agent to move north, we need x4 && ~x1, meaning (s1 || s8) && (~s2 && ~s3).");
        System.out.println("For the agent to move east, we need x1 && ~x2, meaning (s2 || s3) && (~s4 && ~s5).");
        System.out.println("For the agent to move south, we need x2 && ~x3, meaning (s4 || s5) && (~s6 && ~s7).");
        System.out.println("For the agent to move west, we need x3 && ~x4, meaning (s6 || s7) && (~s1 && ~s8).");
        System.out.println();
    }

    public void decide() {
        neuralNetwork.decide();
        System.out.println();
    }
}
