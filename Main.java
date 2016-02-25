package First;

import java.util.LinkedList;

/**
 * @author : Ciprian Baetu
 * @date  : 24 Feb 2016
 *
 *
 */
public class Main {

    private boolean printed = false;
    long totalNumberOfSquares = 0;

    public void exemplu() {
        String alphabets[][] = {{"A", "B", "C"}, {"\u03B1", "\u03B2", "\u03B3"}};
        int[][][] indexes = {{{0, 1, 2}, {1, 2, 0}, {2, 0, 1}}, {{0, 2, 1}, {1, 0, 2}, {2, 1, 0}}};
        System.out.println(toString(indexes, alphabets));
    }


    private String toString(int[][][] indexes, String[][] alphabets) {
        assert alphabets[0].length == alphabets[1].length : "Value of alphabets differ\n";
        int n = alphabets[0].length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                sb.append("(").append(alphabets[0][indexes[0][i][j]]).append(", ").append(alphabets[1][indexes[1][i][j]]);
                sb.append(")");
                if(j < n - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    private void generateSquares(int[][][] indexes, Parameters parameters, int startAtLine, int startAtColumn) {
        Main app = new Main();
        LinkedList<IndexPair> possibilities = getPossibilities(indexes, startAtLine, startAtColumn);
        for(IndexPair pair : possibilities) {
            indexes[0][startAtLine][startAtColumn] = pair.getFirstIndex();
            indexes[1][startAtLine][startAtColumn] = pair.getSecondIndex();
            if((startAtLine == parameters.getNumberOfParameters() - 1) &&
                    (startAtColumn == parameters.getNumberOfParameters() - 1)) {
                ++totalNumberOfSquares;
                if(!printed) {
                    System.out.println("Exemplu de patrat greco-latin cu " +
                            String.valueOf(parameters.getNumberOfParameters()) + " laturi:");
                    System.out.println(app.toString(indexes, parameters.getAlphabets()));
                    printed = true;
                }
            }
            else {
                if(startAtColumn == parameters.getNumberOfParameters() - 1) {
                    generateSquares(indexes, parameters, startAtLine + 1, 0);
                }
                generateSquares(indexes, parameters, startAtLine, startAtColumn + 1);

            }
        }
    }

    private void computeSolution(Parameters parameters) {
        int[][][] indexes = new int[2][parameters.getNumberOfParameters()][parameters.getNumberOfParameters()];
        generateSquares(indexes, parameters, 0, 0);
        System.out.println("In total exista: " + String.valueOf(totalNumberOfSquares) + " patrate greco latine de latura " +
                String.valueOf(parameters.getNumberOfParameters()));
    }

    private LinkedList<IndexPair> getPossibilities(int[][][] indexes, int actualFirstIndex, int actualSecondIndex) {
        Possibilities possibilities = new Possibilities(indexes[0].length);
        for(int i = 0; i <= actualFirstIndex; ++i) {
            for (int j = 0; j < indexes[1].length; ++j) {
                // we traverse the indexes matrix to see what possibilities we have right now
                if ((i < actualFirstIndex) || (i == actualFirstIndex && j < actualSecondIndex)) {
                    if(i == actualFirstIndex || j == actualSecondIndex) {
                        possibilities.addFirstUsedValues(indexes[0][i][j]);
                        possibilities.addSecondUsedValues(indexes[1][i][j]);
                    }
                    possibilities.addPairValue(new IndexPair(indexes[0][i][j], indexes[1][i][j]));
                }
            }
        }

        return possibilities.getPossibleValues();
    }


    public static void main(String[] args) {

        Main app = new Main();
        System.out.println("Exemplul din enunt este: ");
        app.exemplu();
        Parameters parameters = new Parameters(args);
        if(parameters.getNumberOfParameters() == 2 || parameters.getNumberOfParameters() == 6) {
            System.out.println("Nu exista patrate greco latine de latura " +
                    String.valueOf(parameters.getNumberOfParameters()));
        }
        IndexPair.maxIndex = parameters.getNumberOfParameters();
        // we made a pseudo-CLI with Parameters class
        app.computeSolution(parameters);


    }
}
