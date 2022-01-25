
public class DynamicPro {

    /**
     * 
     * @param first
     *            String inputed = V
     * 
     * @param second
     *            string inputed = W
     * @param scoringMatrix
     *            scoring matrix. First number is the match score, second is the
     *            mismatch, third is the indel score
     * @param alignment string saying either global or local depending on the problem
     */
    public static void main(
        String first,
        String second,
        int[] scoringMatrix,
        String alignment) {
        String v = first;
        String w = second;
        String command = alignment;
        int match = scoringMatrix[0];
        int mismatch = scoringMatrix[1];
        int indel = scoringMatrix[2];
        SharedFunctions sf = new SharedFunctions(v, w, command, match, mismatch,
            indel);
        Global global = new Global(v, w, sf);
        Local local = new Local(v, w, sf);
        Block[][] filledTable = new Block[v.length()][w.length()];

        // Third command line input is for either problem
        if (command.equals("global")) {
            // global alignment

            global.buildABear();
            filledTable = global.getTable();
            sf.printTable(filledTable);

            global.traceback();

        }
        else {
            // local alignment

            local.buildABear();
            filledTable = local.getTable();
            sf.printTable(filledTable);

            local.traceback();
        }
    }

}
