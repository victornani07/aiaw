import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<LinkedList<Character>> table = new ArrayList<>();

        LinkedList<Character> firstColumn = new LinkedList<>();
        firstColumn.add('B');

        LinkedList<Character> secondColumn = new LinkedList<>();
        secondColumn.add('C');
        secondColumn.add('A');

        LinkedList<Character> thirdColumn = new LinkedList<>();

        table.add(firstColumn);
        table.add(secondColumn);
        table.add(thirdColumn);

        LinkedList<Character> desiredState = new LinkedList<>();
        desiredState.add('C');
        desiredState.add('B');
        desiredState.add('A');

        Solution solution = new Solution(table, desiredState);

        solution.solve();
    }
}
