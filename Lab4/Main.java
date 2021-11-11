public class Main {
    public static void main(String[] args) {

        // Clasa de baza pentru ilustrarea functionalitatilor descrise in celelalte clase
        MazeCreator mazeCreator = new MazeCreator();

        Room[][] maze = mazeCreator.getMaze();

        Solution solution = new Solution(maze);

        solution.solve();
    }
}
