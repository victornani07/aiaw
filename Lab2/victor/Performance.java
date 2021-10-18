package victor;

public class Performance {
    private int states;
    private int moves;
    private final int threesholdPerformance;

    public Performance(int threesholdPerformance) {
        states = 0;
        moves = 0;
        this.threesholdPerformance = threesholdPerformance;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getStates() {
        return states;
    }

    public int getMoves() {
        return moves;
    }

    public int getThreesholdPerformance() {
        return threesholdPerformance;
    }

    public void showResults() {
        System.out.println("----------------");
        System.out.println("Performance");
        System.out.println("----------------");
        System.out.println("Number of total states: " + states + ".");
        System.out.println("Number of total moves: " + moves + ".");
        System.out.println("The maximum number of moves we want to keep in touch with: " + getThreesholdPerformance() + ".");
        System.out.println("Efficiency: " + (double)getThreesholdPerformance() / getMoves() + " faster.");
    }
}
