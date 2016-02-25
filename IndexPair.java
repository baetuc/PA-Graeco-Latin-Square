package First;

/**
 * Created by Cip on 25-Feb-16.
 */
public class IndexPair {

    static int maxIndex = 0;

    private int firstIndex;
    private int secondIndex;

    public IndexPair(int firstIndex, int secondIndex) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }


    public int getFirstIndex() {
        return firstIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof IndexPair)) {
            return false;
        }
        IndexPair rhs = (IndexPair) obj;
        return (this.firstIndex == ((IndexPair) obj).firstIndex &&
                this.secondIndex == ((IndexPair) obj).secondIndex);
    }

    @Override
    public int hashCode() {
        return firstIndex * maxIndex + secondIndex;
    }
}
