public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        // Am considerat 2 exemple
        // Agentul 1 va parcurge perimetrul camerei
        // Agentul 2 va parcurge perimetrul obiectului conturat cu negru

         Agent agent1 = new Agent(9, 10,  'N', grid);
         agent1.move();

        // Agent agent2 = new Agent(2, 6, 'S', grid);
        // agent2.move();
    }
}
