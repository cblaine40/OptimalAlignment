/**
 * 
 * @author camil
 *
 */
public class Global {

    private Block[][] table;
    private String optimalV;
    private String optimalW;
    private String v;
    private String w;
    private int sum;

    private SharedFunctions sf;
    private int i;
    private int j;

    /**
     * Global
     */
    public Global(String v, String w, SharedFunctions s) {
        this.v = v;
        this.w = w;
        i = v.length();
        j = w.length();
        sf = s;
        table = new Block[i + 1][j + 1];

    }


    /**
     * fresh table with base cases
     */
    public void intializeTable() {

        table[0][0] = new Block(0, "*");

        // Initialize the j
        int initial = -1;
        for (int z = 1; z <= j; z++) {
            table[0][z] = new Block(initial, "l");
            initial--;

        }

        initial = -1;
        for (int t = 1; t <= i; t++) {
            table[t][0] = new Block(initial, "u");
            initial--;
        }
        

    }


    /**
     * Return table
     */
    public Block[][] getTable() {
        return table;
    }


    /**
     * Build table
     */
    public void buildABear() {
        this.intializeTable();
        for (int beginCol = 1; beginCol <= j; beginCol++) {
            for (int beginRow = 1; beginRow <= i; beginRow++) {

                String direc = sf.getBestDirection(beginRow, beginCol, table);
                int wei = sf.getMaxWeight(beginRow, beginCol, table);
                table[beginRow][beginCol] = new Block(wei, direc);

            }
        }
    }


    /**
     * Back trace
     */
    public String traceback() {
        StringBuilder backwardsV = new StringBuilder();
        StringBuilder backwardsW = new StringBuilder();

        int startI = i - 1;
        int startJ = j - 1;
        Block temp = table[i][j];

        // need to make for loop to walk backwards
        while (temp.getArrow() != "*") {

            if (temp.getArrow() == "d" || temp.getArrow() == "ud" || temp
                .getArrow() == "dl" || temp.getArrow() == "udl") {
                backwardsV.append(v.charAt(startI));
                backwardsW.append(w.charAt(startJ));

                startI--;
                startJ--;
            }
            else if (temp.getArrow() == "l" || temp.getArrow() == "ul") {
                backwardsV.append('-');
                backwardsW.append(w.charAt(startJ));

                startJ--;

            }
            else {
                backwardsV.append(v.charAt(startI));

                backwardsW.append('-');
                startI--;
            }
            temp = table[startI + 1][startJ + 1];

        }

        optimalV = backwardsV.reverse().toString();
        optimalW = backwardsW.reverse().toString();

        sum = sf.sum(optimalV, optimalW);

        String traced = (optimalV + "\n" + optimalW);
        System.out.println("One Optimal Global Alignment:");
        System.out.println(traced);
        System.out.println("Score:");
        System.out.println(sum);

        return traced;

    }


    /**
     * Return the score
     * 
     * @return
     */
    public int getSum() {
        return sum;
    }

}
