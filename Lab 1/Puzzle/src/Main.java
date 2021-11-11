public class Main {

    public static void main(String[] args) {
        int[][] initialState = {
                {1, 2, 3},
                {0, 4, 6},
                {7, 5, 8}
        };

        int[][] finalState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        PuzzleSolver puzzleSolver = new PuzzleSolver(initialState, finalState, 1, 0);
        puzzleSolver.solve();
    }
}
