package mage.collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author TheElk801
 */
public abstract class BoosterStructure {

    private final List<CardRun> slots;

    protected BoosterStructure(CardRun... runs) {
        this.slots = Arrays.asList(runs);
    }

    public List<String> makeRun() {
        List<String> cards = new ArrayList<>();
        for (CardRun run : this.slots) {
            cards.add(run.getNext());
        }
        return cards;
    }

    public void shuffle() {
        for (CardRun run : this.slots) {
            run.shuffle();
        }
    }
}
