import java.util.ArrayList;
import java.util.List;

// Clasa ce descrie camerele din lumea Wampus
// practic lumea Wampus este formata dintr-o matrice 4x4 de astfel de camere
// Evident, fiecare camera are o lista ce poate avea maxim 3 elemente
// (in cazul cand avem aur, miros si briza) sau 2 elemente, respectiv 1 element
// Avem 2 metode - addAspect, ce adauga aspecte la o camera si getRoomAspects
// care ne intoarce lista cu aspecte ale unei camere

public class Room {
    private final List<Integer> roomAspects;

    public Room() {
        roomAspects = new ArrayList<>();
    }

    public void addAspect(int roomAspect) {
        roomAspects.add(roomAspect);
    }

    public List<Integer> getRoomAspects() {
        return roomAspects;
    }
}
