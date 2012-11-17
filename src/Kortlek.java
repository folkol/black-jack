import java.util.Collections;
import java.util.Vector;

public class Kortlek {
    Vector<Kort> cards;

    public Kortlek() {
        cards = new Vector<Kort>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Kort(i, j));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Kort draw() {
        return cards.remove(0);
    }
}