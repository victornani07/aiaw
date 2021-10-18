import java.util.ArrayList;
import java.util.List;

public class PuzzleSolver {

    private final int[][] initialState;
    private final int[][] finalState;
    private final int initialI;
    private final int initialJ;

    // Initializam clasa care rezolva puzzle-ul
    // Include starea intiala, starea finala
    // Dar si pozitia initiala a spatiului gol
    public PuzzleSolver(int[][] initialState, int[][] finalState, int initialI, int initialJ) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.initialI = initialI;
        this.initialJ = initialJ;
    }

    // Metoda ce determina valoarea functiei h
    private int hFunction(int[][] state) {
        int count = 0;

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 3; ++j)
                if(state[i][j] != finalState[i][j])
                    ++count;

        return count;
    }

    // Metoda ce determina valoarea functiei f
    private int getFValue(int[][] table, int g) {
        int gValue = g + 1;
        int hValue = hFunction(table);

        return hValue + gValue;
    }

    // Metoda ce copiaza elementele unei matrici in una noua
    private int[][] cloneMatrix(int[][] matrix) {
        int[][] clone = new int[3][3];

        for(int i = 0; i < 3; ++i)
            System.arraycopy(matrix[i], 0, clone[i], 0, 3);

        return clone;
    }

    // Metoda ce verifica daca e posibil sa mutam o piesa sus
    // Iar daca e posibil, o mutam, salvand noua stare
    private void moveUp(int[][] state, Position position, int i, int j) {
        if(i - 1 < 0)
            return;

        int temp = state[i - 1][j];
        state[i - 1][j] = state[i][j];
        state[i][j] = temp;
        position.setI(i - 1);
    }

    // Metoda ce verifica daca e posibil sa mutam o piesa jos
    // Iar daca e posibil, o mutam, salvand noua stare
    private void moveDown(int[][] state, Position position, int i, int j) {
        if(i + 1 >= 3)
            return;

        int temp = state[i + 1][j];
        state[i + 1][j] = state[i][j];
        state[i][j] = temp;
        position.setI(i + 1);
    }

    // Metoda ce verifica daca e posibil sa mutam o piesa in stanga
    // Iar daca e posibil, o mutam, salvand noua stare
    private void moveLeft(int[][] state, Position position, int i, int j) {
        if(j - 1 < 0)
            return;

        int temp = state[i][j - 1];
        state[i][j - 1] = state[i][j];
        state[i][j] = temp;
        position.setJ(j - 1);
    }

    // Metoda ce verifica daca e posibil sa mutam o piesa in dreapta
    // Iar daca e posibil, o mutam, salvand noua stare
    private void moveRight(int[][] state, Position position, int i, int j) {
        if(j + 1 >= 3)
            return;

        int temp = state[i][j + 1];
        state[i][j + 1] = state[i][j];
        state[i][j] = temp;
        position.setJ(j + 1);
    }

    // Metoda ce verifica daca n-am mai trecut prin starea respectiva
    // Pentru a evita un ciclu infinit
    private boolean has(List<int[][]> visited, int[][] state) {
        int c = 0;
        for(int[][] s : visited) {
            int count = 0;
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j)
                    if (s[i][j] == state[i][j])
                        ++count;
            if(count == 9)
                ++c;
        }

        return c == 1;
    }

    // Metoda ce afiseaza starea data ca parametru
    private void print(int[][] state) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j)
                System.out.print(state[i][j] + " ");
            System.out.println();
        }

        System.out.println();
    }


    // Metoda de baza
    // Salvam starea initiala in spatiul starilor vizitate si pornim de la ea
    // De fiecare data mutam piesa in toate directiile posibile, creand o noua stare
    // Verificam daca starea e una valida
    // Iar daca da, o comparam cu celelalte stari valide in ceea ce tine de valoarea functiei f
    // Repetam acesti pasi pana ajungem la starea dorita
    // Starea dorita e atinsa atunci cand valoarea functiei h e 0, adica toate piesele sunt la locul lor
    public void solve() {
        int[][] state = cloneMatrix(initialState);
        List<int[][]> visited = new ArrayList<>();
        visited.add(initialState);
        int i = initialI;
        int j = initialJ;
        int gValue = 0;
        print(state);

        while(hFunction(state) != 0) {
            int[][] leftState = cloneMatrix(state);
            int[][] rightState = cloneMatrix(state);
            int[][] upState = cloneMatrix(state);
            int[][] downState = cloneMatrix(state);
            Position leftPos = new Position(i, j);
            Position rightPos = new Position(i, j);
            Position upPos = new Position(i, j);
            Position downPos = new Position(i, j);
            int minF = Integer.MAX_VALUE;
            char dir = '-';

            moveLeft(leftState, leftPos, i, j);
            moveRight(rightState, rightPos, i, j);
            moveDown(downState, downPos, i, j);
            moveUp(upState, upPos, i, j);

            if(leftPos.getJ() != j) {
                int fValue = getFValue(leftState, gValue);
                if(fValue < minF && !has(visited, leftState)) {
                    minF = fValue;
                    dir = 'l';
                }
            }

            if(rightPos.getJ() != j) {
                int fValue = getFValue(rightState, gValue);
                if(fValue < minF && !has(visited, rightState)) {
                    minF = fValue;
                    dir = 'r';
                }
            }

            if(upPos.getI() != i) {
                int fValue = getFValue(upState, gValue);
                if(fValue < minF && !has(visited, upState)) {
                    minF = fValue;
                    dir = 'u';
                }
            }

            if(downPos.getI() != i) {
                int fValue = getFValue(downState, gValue);
                if(fValue < minF && !has(visited, downState))
                    dir = 'd';
            }

            if(dir == 'd') {
                state = downState;
                visited.add(downState);
                i = downPos.getI();
                j = downPos.getJ();
            }
            else if(dir == 'u') {
                state = upState;
                visited.add(upState);
                i = upPos.getI();
                j = upPos.getJ();
            }
            else if(dir == 'l') {
                state = leftState;
                visited.add(leftState);
                i = leftPos.getI();
                j = leftPos.getJ();
            }
            else {
                state = rightState;
                visited.add(rightState);
                i = rightPos.getI();
                j = rightPos.getJ();
            }

            print(state);
        }
    }
}
