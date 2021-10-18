// Clasa folosita in momentul in care vom vrea sa mutam un bloc de pe o coloana pe alta
// Stim deja ca de fiecare data vom avea ocazia sa mutam in doua locatii
// Aceasta clasa ne ajuta sa stabilim in care din cele doua locatii putem face mutarea
// tinand cont de pozitia curenta

public class Tuple {
    private final int index1;
    private final int index2;

    public Tuple(int i) {
        if(i == 0) {
            index1 = 1;
            index2 = 2;
        } else if(i == 1) {
            index1 = 0;
            index2 = 2;
        } else {
            index1 = 0;
            index2 = 1;
        }
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }
}
