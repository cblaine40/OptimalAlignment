
public class Local {
    private String v;
    private String w;
    private Block[][] table;
    private SharedFunctions sf;
    private int i;
    private int j;
    private String optimalV;
    private String optimalW;
    private int iLargest;
    private int jLargest;

    public Local(String v, String w, SharedFunctions s) {
        this.v = v;
        this.w = w;
        i = v.length();
        j = w.length();
        sf = s;
        table = new Block[i + 1][j + 1];
    }


    public void intializeTable() {

        for (int h = 0; h <= i; h++) {
            table[h][0] = new Block(0, "*");
        }

        for (int k = 0; k <= j; k++) {
            table[0][k] = new Block(0, "*");
        }
    }


    public void buildABear() {
        int largestWeight = 0;
        this.intializeTable();
        for (int beginCol = 1; beginCol <= j; beginCol++) {
            for (int beginRow = 1; beginRow <= i; beginRow++) {
                String direc = new String();
                int weight = sf.getMaxWeight(beginRow, beginCol, table);

                // for local if weight is less than 0 than no arrow and weight
                // is 0
                if (weight < 0) {
                    direc = "*";
                    weight = 0;
                }
                else {

                    direc = sf.getBestDirection(beginRow, beginCol, table);

                    // get the starting point for the traceback
                    if (largestWeight < weight) {
                        largestWeight = weight;
                        iLargest = beginRow;
                        jLargest = beginCol;

                    }

                }

                table[beginRow][beginCol] = new Block(weight, direc);
            }
        }
    }


    public int getiLargest() {
        return iLargest;
    }


    public int getjLargest() {
        return jLargest;
    }


    public Block[][] getTable() {
        return table;
    }


    /**
     * Back trace
     */
    public String traceback() {
        StringBuilder backwardsV = new StringBuilder();
        StringBuilder backwardsW = new StringBuilder();
        int startI;
        int startJ;

        startI = this.getiLargest() - 1;
        startJ = this.getjLargest() - 1;

        Block temp = table[startI + 1][startJ + 1];

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

        int sum = sf.sum(optimalV, optimalW);
        String traced = (optimalV + "\n" + optimalW);
        System.out.println("One Optimal Local Alignment:");
        System.out.println(traced);
        System.out.println("Score:");
        System.out.println(sum);

        return traced;

    }

}
