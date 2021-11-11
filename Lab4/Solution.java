import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final Room[][] maze;

    public Solution(Room[][] maze) {
        this.maze = maze;
    }

    // Metoda ce adauga in lista camerelor vizitate o noua camera, pentru a evita
    // procesarea acesteia de mai multe ori
    private void updateVisited(List<Tuple> visited, Tuple tuple) {
        visited.add(tuple);
    }

    // Verifica daca o anumita camera a fost deja vizitata
    private boolean checkVisited(List<Tuple> visited, Tuple tuple) {
        for(Tuple t : visited) {
            if (t.getI() == tuple.getI() && t.getJ() == tuple.getJ())
                return true;
        }

        return false;
    }

    // Metoda de baza ce asigura traseul sigur dintre agent si comoara
    public void solve() {
        int i = 3, j = 0, changedDirection = 0;
        char direction = 'E';

        // Matrice ce contine potentialele pozitii ale lui Wampus, pozitii procesate
        // pe parcursul traversarii camerelor
        int[][] wampusPositions = new int[4][4];
        // Matrice ce contine potentialele pozitii ale abisurilor, pozitii procesate
        // pe parcursul traversarii camerelor
        int[][] pitPositions = new int[4][4];
        List<Tuple> visited = new ArrayList<>();
        visited.add(new Tuple(i, j));
        boolean movedBackwards = false;

        // Ciclu infinit care se opreste in momentul gasirii comorii
        // Intr-o lume structurata corespunzator, va exista un drum sigur
        // Catre agent si comoara, ce va exista si o conditie de oprire, si anume
        // Momentul gasirii comorii
        while(true) {
            System.out.println("Position (" + i + ", " + j + "), facing " + direction + ".");

            // Inainte de a merge mai departe, analizam camera respectiva
            // Initial, camera este sigura, este punctul de pornire
            Room room = maze[i][j];

            // Aceasta procesare a camerei va avea loc doar in cazul in care am avansat
            // Schimbarea directiei de orientare nu e considerata avansare si ar cauza
            // Procesarea unei anumite camere de mai multe ori, lucru pe care nu ni-l dorim
            // Totodata, metoda contine si o functionalitate de backtracking activata in momentul
            // In care am epuizat toate directiile de orientare ale unei camere si nu mai putem face nimic
            // din camera respectiv
            // Respectiv, ne mutam inapoi in camera din care am avansat spre camera respectiva
            // Deci in cazul in care am facut backtracking si am revenit in camera anterioara, care
            // a fost deja procesata, nu mai are sens sa o procesam din nou
            if(changedDirection == 0 && !movedBackwards) {
                boolean foundBreeze = false, foundStench = false;
                // Iteram prin aspectele camerei
                for (int aspect : room.getRoomAspects()) {
                    // Daca am gasit comoara, am terminat jocul, respectiv metoda se incheie
                    if (aspect == Aspects.GOLD) {
                        System.out.println("Gold was found at position (" + i + ", " + j + ").");
                        return;
                    }

                    // Daca am dat de briza, exista sansa ca in camerele adiacente sa fie un abis
                    if (aspect == Aspects.BREEZE) {
                        System.out.println("Breeze was found at (" + i + ", " + j + ") position.");

                        // urmatoarele 4 if-uri fiecare din cele 4 potentiale camere(daca exista, adica
                        // care sunt in limitele matricei) daca contin abisuri
                        // Evident, spatiile deja procesate, adica din care am venit, nu contin, pentru ca de altfel
                        // n-am mai fi avansat de frica sa nu picam in abis
                        // Mai avem si o componenta notata cu cifra 2 in matricea potentialelor abisuri
                        // Am considerat aceasta situatie in cazul in care exista riscul sa fie si Wampus, si abis
                        // Deci daca intr-o anumita celula am gasit un miros specific, dar briza nu, atunci
                        // e clar ca in camerele adiacente e doar Wampus, nu si briza
                        // Deci notam camerele cu 2, semnificand ca nu exista abis
                        // Deci nu avem voie sa specificam abisuri in pozitiile respective
                        if(j + 1 < 4 && !checkVisited(visited, new Tuple(i, j + 1)) && pitPositions[i][j + 1] != 2)
                            pitPositions[i][j + 1] = 1;

                        if(i + 1 < 4 && !checkVisited(visited, new Tuple(i + 1, j)) && pitPositions[i + 1][j] != 2)
                            pitPositions[i + 1][j] = 1;

                        if(j - 1 >= 0 && !checkVisited(visited, new Tuple(i, j - 1)) && pitPositions[i][j - 1] != 2)
                            pitPositions[i][j - 1] = 1;

                        if(i - 1 >= 0 && !checkVisited(visited, new Tuple(i - 1, j)) && pitPositions[i - 1][j] != 2)
                            pitPositions[i - 1][j] = 1;

                        foundBreeze = true;
                    }

                    // Situatia abisurilor se repeta si-n cazul Wampusului, fiind acelasi principiu
                    if (aspect == Aspects.STENCH) {
                        System.out.println("Stench was found at (" + i + ", " + j + ") position.");

                        if(j + 1 < 4 && !checkVisited(visited, new Tuple(i, j + 1)) && wampusPositions[i][j + 1] != 2)
                            wampusPositions[i][j + 1] = 1;

                        if(i + 1 < 4 && !checkVisited(visited, new Tuple(i + 1, j)) && wampusPositions[i + 1][j] != 2)
                            wampusPositions[i + 1][j] = 1;

                        if(j - 1 >= 0 && !checkVisited(visited, new Tuple(i, j - 1)) && wampusPositions[i][j - 1] != 2)
                            wampusPositions[i][j - 1] = 1;

                        if(i - 1 >= 0 && !checkVisited(visited, new Tuple(i - 1, j)) && wampusPositions[i - 1][j] != 2)
                            wampusPositions[i - 1][j] = 1;

                        foundStench = true;
                    }

                    // Facem verificarea de care am specificat mai sus, si anume daca am gasit
                    // briza, iar miros nu, specificam ca nu exista Wampus in camerele adiacente
                    // si invers, ca exista abis, si nu Wampus(in dependenta de situatie)
                    if(foundBreeze && !foundStench) {
                        if(j + 1 < 4 && !checkVisited(visited, new Tuple(i, j + 1)))
                            wampusPositions[i][j + 1] = 2;

                        if(i + 1 < 4 && !checkVisited(visited, new Tuple(i + 1, j)))
                            wampusPositions[i + 1][j] = 2;

                        if(j - 1 >= 0 && !checkVisited(visited, new Tuple(i, j - 1)))
                            wampusPositions[i][j - 1] = 2;

                        if(i - 1 >= 0 && !checkVisited(visited, new Tuple(i - 1, j)))
                            wampusPositions[i - 1][j] = 2;
                    } else if(!foundBreeze && foundStench) {
                        if(j + 1 < 4 && !checkVisited(visited, new Tuple(i, j + 1)))
                            pitPositions[i][j + 1] = 2;

                        if(i + 1 < 4 && !checkVisited(visited, new Tuple(i + 1, j)))
                            pitPositions[i + 1][j] = 2;

                        if(j - 1 >= 0 && !checkVisited(visited, new Tuple(i, j - 1)))
                            pitPositions[i][j - 1] = 2;

                        if(i - 1 >= 0 && !checkVisited(visited, new Tuple(i - 1, j)))
                            pitPositions[i - 1][j] = 2;
                    }
                }
            }

            boolean advance = false;

            // Aici e o functionalitate de baza, si anume cea de avansare in dependenta
            // de directia in care suntem acum
            // Respectiv, resetam flagurile de backwards, schimbare a directiei, care sunt noi
            // pentru noua camera
            // In cazul in care am putut avansa, setam flag-ul advance corespunzator
            if (direction == 'E' && j + 1 < 4 && pitPositions[i][j + 1] != 1 && wampusPositions[i][j + 1] != 1
                    && !checkVisited(visited, new Tuple(i, j + 1))) {
                changedDirection = 0;
                ++j;
                advance = true;
                movedBackwards = false;
                updateVisited(visited, new Tuple(i, j));
            } else if (direction == 'S' && i + 1 < 4 && pitPositions[i + 1][j] != 1 && wampusPositions[i + 1][j] != 1
                    && !checkVisited(visited, new Tuple(i + 1, j))) {
                changedDirection = 0;
                ++i;
                advance = true;
                movedBackwards = false;
                updateVisited(visited, new Tuple(i, j));
            } else if (direction == 'V' && j - 1 >= 0 && pitPositions[i][j - 1] != 1 && wampusPositions[i][j - 1] != 1
                    && !checkVisited(visited, new Tuple(i, j - 1))) {
                changedDirection = 0;
                --j;
                advance = true;
                movedBackwards = false;
                updateVisited(visited, new Tuple(i, j));
            } else if (direction == 'N' && i - 1 >= 0 && pitPositions[i - 1][j] != 1 && wampusPositions[i - 1][j] != 1
                    && !checkVisited(visited, new Tuple(i - 1, j))) {
                changedDirection = 0;
                --i;
                advance = true;
                movedBackwards = false;
                updateVisited(visited, new Tuple(i, j));
            }

            // Daca n-am putut avansa, inseamna ca e un moment prielnic
            // sa schimbam directia de orientare
            if(!advance) {
                if(direction == 'N')
                    direction = 'E';
                else if(direction == 'E')
                    direction = 'S';

                else if(direction == 'S')
                    direction = 'V';
                else
                    direction = 'N';
                ++changedDirection;
            }

            // Daca am schimbat directia de orientare de 4 ori
            // Inseamna ca suntem intr-un punct mort si trebuie sa activam
            // componenta de backtracking pentru a reveni in camera precedenta
            if(changedDirection == 4) {
                movedBackwards = true;
                if (direction == 'E')
                    --j;
                else if (direction == 'S')
                    --i;
                else if (direction == 'V')
                    ++j;
                else
                    ++i;
            }

            // Respectiv, procesul de mai sus se repeta pana gasim comoara
        }

    }
}
