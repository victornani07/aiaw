// Clasa destinata initializarii perimetrului unde are loc miscarea agentului
// FREE reprezinta celulele goale
// OBJECT reprezinta celulele unde este pozitionat obiectul cu necru
// BORDER reprezinta granitile intregului perimetru
// Pentru facilitate, am plasat granite si in spatiile care nu fac parte din perimetru

public class Grid {
    public static final int FREE = 1;
    public static final int OBJECT = 2;
    public static final int BORDER = 4;

    private final int[][] grid;

    public Grid() {
        grid = new int[][] {
                { BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER},
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER},
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, OBJECT, OBJECT, OBJECT, OBJECT, OBJECT, OBJECT, FREE, FREE, FREE, FREE, FREE, FREE, BORDER},
                { BORDER, FREE, FREE, OBJECT, OBJECT, FREE, FREE, OBJECT, OBJECT, FREE, FREE, FREE, FREE, FREE, FREE, BORDER},
                { BORDER, FREE, FREE, OBJECT, OBJECT, FREE, FREE, OBJECT, OBJECT, FREE, FREE, FREE, FREE, FREE, FREE, BORDER},
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, FREE, FREE, FREE, FREE, FREE, BORDER, BORDER, BORDER, FREE, FREE, FREE, BORDER, BORDER, BORDER, BORDER },
                { BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER, BORDER}
        };
    }

    public int[][] getGrid() {
        return grid;
    }
}
