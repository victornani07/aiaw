// Clasa ce contine pozitia la un moment dat, format din coordonata i si j a matricei

public class Tuple {
    private final int i;
    private final int j;

    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
