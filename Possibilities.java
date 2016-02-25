package First;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Cip on 25-Feb-16.
 */
public class Possibilities {

    int sideLength;

    private HashSet<Integer> firstUsedValues;
    private HashSet<Integer> secondUsedValues;
    private HashSet<IndexPair> usedValues;

    Possibilities(int sideLength) {
        this.sideLength = sideLength;
        firstUsedValues = new HashSet<>();
        secondUsedValues = new HashSet<>();
        usedValues = new HashSet<>();
    }

    public void addFirstUsedValues(int value) {
        firstUsedValues.add(value);
    }

    public void addSecondUsedValues(int value) {
        secondUsedValues.add(value);
    }

    public void addPairValue(IndexPair pair) {
        usedValues.add(pair);
    }

    public LinkedList<IndexPair> getPossibleValues() {
        LinkedList<IndexPair> result = new LinkedList<>();
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                if (!firstUsedValues.contains(i) && !secondUsedValues.contains(j)) {
                    // we may create the pair, but then we must check if the pair is already used
                    IndexPair pair = new IndexPair(i, j);
                    if (!usedValues.contains(pair)) {
                        result.add(pair);
                    }
                }
            }
        }
        return result;
    }
}
