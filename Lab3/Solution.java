import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private final List<LinkedList<Character>> table;
    private final LinkedList<Character> desiredState;
    private final List<LinkedList<Character>> visited;
    private boolean solved;

    // Instantiem clasa de tip Solver cu starea initiala si cu starea dorita
    public Solution(List<LinkedList<Character>> table, LinkedList<Character> desiredState) {
        this.table = table;
        this.desiredState = desiredState;
        visited = new ArrayList<>();
        solved = false;
    }

    // Metoda ce verifica daca am atins starea-tel
    private boolean isDesiredState(LinkedList<Character> state) {
        if(state.size() != 3)
            return false;

        for(int i = 0; i < 3; ++i)
            if(state.get(i) != desiredState.get(i))
                return false;

        return true;
    }

    // Metoda ce adauga o noua stare in spatiul starilor vizitate pentru a evita prelucrarea acesteia de mai multe
    // ori
    // Totodata, algoritmul din spatele agentului devine inteligent, intrucat "gandeste" de fiecare data
    // inainte de o miscare
    private void updateVisited(LinkedList<Character> column) {
        if(column.size() > 0) {

            LinkedList<Character> l = new LinkedList<>(column);

            visited.add(l);
        }
    }

    // Metoda ce verifica daca starea ce urmeaza a fi prelucrata e vizitata deja
    // Daca da, atunci o ignora si incearca sa formeze o stare nevizitata pana acum
    public boolean checkIfVisited(LinkedList<Character> column) {
        for(LinkedList<Character> l : visited) {
            if (column.size() != l.size())
                continue;

            int counter = 0, size = l.size();

            for(int i = 0; i < size; ++i)
                if(column.get(i) == l.get(i))
                    ++counter;

            if (counter == size)
                return true;
        }

        return false;
    }

    // Metoda de baza in rezolvarea acestei probleme
    // La fiecare pas, agentul va incerca sa pozitioneze un bloc in una din cele doua coloane
    // Diferenta dintre un algoritm obisnuit e ca va analiza miscarea si va evita sa faca actiuni inutile
    // Sau actiuni care au fost deja efectuate
    // Astfel, se evita asa-numita anomalie Sussman
    // prin faptul ca nu va purta un bloc intr-o coloana care a mai fost vizitata
    public void solve() {
        updateVisited(table.get(0));
        updateVisited(table.get(1));
        updateVisited(table.get(2));

       for(int i = 0; i < 3; ++i)
            if(isDesiredState(table.get(i))) {
                System.out.println();
                solved = true;
                System.out.print("Solved. Column " + i + " is of form: ");
                for(int j = 0; j < table.get(i).size(); ++j) {
                    System.out.print(table.get(i).get(j));
                    if (j != table.get(i).size() - 1)
                        System.out.print(" -> ");
                }
                System.out.println();
                return;
            }

        for(int i = 0; i < 3; ++i) {
            LinkedList<Character> currentColumn = table.get(i);

            if (currentColumn.size() > 0) {
                Character c = currentColumn.pollFirst();
                boolean moved = false;

                Tuple indexes = new Tuple(i);
                int index1 = indexes.getIndex1();
                int index2 = indexes.getIndex2();

                table.get(index1).addFirst(c);
                if (!checkIfVisited(table.get(index1))) {
                    moved = true;
                    System.out.println("Moving cube " + c + " from column " + i + " to column " + index1);
                    updateVisited(table.get(index1));
                    solve();
                }

                if(solved)
                    return;

                table.get(index1).pollFirst();

                table.get(index2).addFirst(c);
                if (!checkIfVisited(table.get(index2))) {
                    moved = true;
                    System.out.println("Moving cube " + c + " from column " + i + " to column " + index2);
                    updateVisited(table.get(index2));
                    solve();
                }

                if(solved)
                    return;

                table.get(index2).pollFirst();

                if(moved)
                    System.out.println("Moving cube " + c + " back to column " + i);
                currentColumn.addFirst(c);
            }
        }
    }
}
