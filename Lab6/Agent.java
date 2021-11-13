// Clasa de baza ce modeleaza miscarea agentului

public class Agent {
    // startingI si startingJ reprezinta coordonatele punctului initial de unde porneste agentul
    // startingDirection reprezinta directia de start a agentului
    // In laborator era specficat nord, dar eu am adaugat acest atribut pentru a putea exersa cu diferite directii intiale
    private final int startingI;
    private final int startingJ;
    private final char startingDirection;
    private final int[][] grid;

    public Agent(int startingI, int startingJ, char startingDirection, Grid grid) {
        this.startingI = startingI;
        this.startingJ = startingJ;
        this.startingDirection = startingDirection;
        this.grid = grid.getGrid();
    }

    // Aceasta metoda trateaza cazurile prezentate in laboratorul 5
    // In dependenta de acei senzori care detecteaza obiectele/granitele
    // Agentul ia o decizie referitoare la directie
    // Pentru fiecare directie, agentul are nevoie doar de 4 senzori din 8, deci am factorizat aceasta functionalitate
    // In metoda respectiva
    // Deci daca doi senzori adiacenti detecteaza un obstacol, iar altii 2 detecteaza un loc prielnic pentru miscare
    // Atunci miscarea se va realiza in acea directie
    private boolean canMove(int s1, int s2, int s3, int s4) {
        return (((s1 == Grid.OBJECT && s2 == Grid.OBJECT) || (s1 == Grid.BORDER && s2 == Grid.BORDER)) &&
                (s3 == 1 && s4 == 1));
    }

    // Metoda de baza pentru miscarea agentului
    // Principiul de functionare e foarte simplu
    // In dependenta de directia initiala si coordonatele agentului in plan, acesta se va deplasa
    // Pana cand va gasi granita grid-ului sau obiectul
    // Ulterior, acesta va parcurge granita in continuu
    // Eu am evitat crearea unei bucle infinite si am preferat sa fac un for care imi ia un numar de pasi, reprezentand
    // practic numarul de miscari al agentului
    // Am preferat aceasta metoda, pentru ca e mai usor de urmarit decat un ciclu infinit
    // Evident, aceasta metoda poate fi adaptata cu usurinta la un ciclu infinit, schimband for-ul in while(true)
    // Metoda respectiva face uz de senzorii agentului si stabiliste unde ar trebui sa realizeze miscarea in momentul in care
    // Intalneste un obstacol
    // In caz ca nu, aceasta realizeaza miscarea in dependenta de directia initiala
    // Totusi, sunt situatii care nu corespund cu cele din laborator, dar care trebuie trate
    // De exemplu, cand doar un singur senzor detecteaza un obstacol
    public void move() {
        int counter = 0;
        int i = startingI;
        int j = startingJ;
        char dir = startingDirection;
        int firstI = -1, firstJ = -1;

        System.out.println("Starting from position (" + i + ", " + j + ").\n");

        for(int k = 0; k < 60; ++k) {
            int s1 = grid[i - 1][j - 1];
            int s2 = grid[i - 1][j];
            int s3 = grid[i - 1][j + 1];
            int s4 = grid[i][j + 1];
            int s5 = grid[i + 1][j + 1];
            int s6 = grid[i + 1][j];
            int s7 = grid[i + 1][j - 1];
            int s8 = grid[i][j - 1];

            boolean e = canMove(s2, s3, s4, s5);
            boolean s = canMove(s4, s5, s6, s7);
            boolean w = canMove(s6, s7, s8, s1);
            boolean n = canMove(s8, s1, s2, s3);

            if(e) {
                ++j;
                dir = 'E';
                System.out.println("Moving towards east. New position is (" + i + ", " + j + ").");
            } else if(s) {
                ++i;
                dir = 'S';
                System.out.println("Moving towards south. New position is (" + i + ", " + j + ").");
            } else if(w) {
                --j;
                dir = 'W';
                System.out.println("Moving towards west. New position is (" + i + ", " + j + ").");
            } else if(n) {
                --i;
                dir = 'N';
                System.out.println("Moving towards north. New position is (" + i + ", " + j + ").");
            } else {
                // Aceasta vericiare se face aici
                // Daca niciuna din cele 4 situatii de la laborator nu sunt intalnite
                // Atunci verificam prezenta unui singur obstacol, realizand miscarea
                // In directia acestuia, pentru a ne mentine in limitele granitelor
                if ((s3 == Grid.OBJECT || s3 == Grid.BORDER) && (s4 == Grid.FREE))
                    dir = 'E';
                else if ((s5 == Grid.OBJECT || s5 == Grid.BORDER) && (s6 == Grid.FREE))
                    dir = 'S';
                else if ((s7 == Grid.OBJECT || s7 == Grid.BORDER) && (s8 == Grid.FREE))
                    dir = 'W';
                else if ((s1 == Grid.OBJECT || s1 == Grid.BORDER) && (s2 == Grid.FREE))
                    dir = 'N';

                // In caz ca nu gasim niciun obstacol, ne deplasam conform cu directia precedenta
                // Care, in punctul de start, reprezinta directia pe care o specificam noi
                // Ulterior, aceasta este actualizata conform conditiilor de mai sus
                if (dir == 'N') {
                    --i;
                    System.out.println("Moving towards north. New position is (" + i + ", " + j + ").");
                } else if (dir == 'S') {
                    ++i;
                    System.out.println("Moving towards south. New position is (" + i + ", " + j + ").");
                } else if (dir == 'E') {
                    ++j;
                    System.out.println("Moving towards east. New position is (" + i + ", " + j + ").");
                } else {
                    --j;
                    System.out.println("Moving towards west. New position is (" + i + ", " + j + ").");
                }
            }

            // Aceasta functionalitate permite depistarea pozitiei cand agentul a gasit pentru prima data marginea
            // si afisarea unui mesaj de fiecare data cand acesta ajunge in acel punct din nou
            if(i == firstI && j == firstJ) {
                System.out.println("The agent has reached the point where he first found the border.");
            }
            if((e || s || w || n) && counter == 0) {
                ++counter;
                firstI = i;
                firstJ = j;
                System.out.println("The coordinates of the agent where he has found the border are (" + i + ", " + j + ").");
            }

        }

    }
}
