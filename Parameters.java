package First;

import java.util.Random;

/**
 * @author Ciprian Baetu
 * @date 25 Feb 2016
 */
public class Parameters {

    final private int MAX_NUMBER_OF_LETTERS = 26;

    private int numberOfParameters;
    private String[][] alphabets;

    public Parameters(String[] args) {
        assert args.length == 3 || args.length == 0 : "Not exactly 3 arguments.\n";
        if (args.length == 0) {
            // we generate random parameters
            generateParameters();
        } else {
            // we already have parameters to parse
            this.numberOfParameters = Integer.parseInt(args[0]);
            assert this.numberOfParameters == args[1].length() && this.numberOfParameters == args[2].length() :
                    "Alphabets don't have the desired length.\n";
            alphabets = new String[2][numberOfParameters];
            for (int i = 0; i < numberOfParameters; ++i) {
                alphabets[0][i] = String.valueOf(args[1].charAt(i));
                alphabets[1][i] = String.valueOf(args[2].charAt(i));
            }
        }
    }

    private void generateParameters() {
        Random rnd = new Random();
        this.numberOfParameters = rnd.nextInt(MAX_NUMBER_OF_LETTERS) + 1;
        this.alphabets = new String[2][this.numberOfParameters];
        for (int i = 0; i < this.numberOfParameters; ++i) {
            this.alphabets[0][i] = String.valueOf('A' + i);
            this.alphabets[1][i] = String.valueOf('\u03B1' + i);
        }
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public String[][] getAlphabets() {
        return alphabets;
    }

}
