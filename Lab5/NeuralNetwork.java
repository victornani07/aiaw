public class NeuralNetwork {
    private final int[] sensorySignals;

    public NeuralNetwork(int[] s) {
        sensorySignals = new int[8];

        System.arraycopy(s, 0, sensorySignals, 0, 8);
    }
    // Vom lua in considerare toate cazurile tratate la laborator
    // Pentru a decide in care directie o poate lua agentul
    // Intrucat indexarea in Java incepe de la 0
    // Indecsii s-urilor for mutate cu o pozitie in urma

    // x4 && ~x1 <==> (s1 || s8) && (~s2 && ~s3)
    private boolean canMoveNorth() {
        return ((sensorySignals[0] == 1 || sensorySignals[7] == 1) &&
                (sensorySignals[1] == 0 && sensorySignals[2] == 0));
    }

    // x1 && ~x2 <==> (s2 || s3) && (~s4 && ~s5)
    private boolean canMoveEast() {
        return ((sensorySignals[1] == 1 || sensorySignals[2] == 1) &&
                (sensorySignals[3] == 0 && sensorySignals[4] == 0));
    }

    // x2 && ~x3 <==> (s4 || s5) && (~s6 && ~s7)
    private boolean canMoveSouth() {
        return ((sensorySignals[3] == 1 || sensorySignals[4] == 1) &&
                (sensorySignals[5] == 0 && sensorySignals[6] == 0));
    }

    // x3 && ~x4 <==> (s6 || s7) && (~s1 && ~s8)
    private boolean canMoveWest() {
        return ((sensorySignals[5] == 1 || sensorySignals[6] == 1) &&
                (sensorySignals[0] == 0 && sensorySignals[7] == 0));
    }

    // Cand tot perimetrul e liber, agentul face miscarea standard
    // Si se deplaseaza catre nord
    private boolean standardMove() {
        for(int sensorySignal : sensorySignals)
            if(sensorySignal == 1)
                return false;

        return true;
    }

    public void decide() {
        if(standardMove()) {
            System.out.println("The agent can move to all directions, since the whole perimeter is free.");
            return;
        }

        if(canMoveNorth()) { System.out.println("The agent can move to north."); return; }
        if(canMoveEast()) { System.out.println("The agent can move to east."); return; }
        if(canMoveWest()) { System.out.println("The agent can move to west."); return; }
        if(canMoveSouth()) System.out.println("The agent can move to south.");
    }
}
