/**
 * Class that has the same functions for global and local alignment
 * 
 * @author camil
 *
 */
public class SharedFunctions {

    // Characters pointing in a direction
    private String up;
    private String left;
    private String dia;
    private String upLeft;
    private String upDia;
    private String leftDia;
    private String udl;
    private String v;
    private String w;
    private Block[][] table;
    private int i;
    private int j;
    private int match;
    private int mismatch;
    private int gap;

    /**
     * Shared func constructor
     */
    public SharedFunctions(String first, String second, String alignment, int match, int mismatch, int indel) {
        v = first;
        w = second;
        

        this.match = match;
        this.mismatch = mismatch;
        this.gap = indel;
        // Set the lengths to i and j
        i = v.length();
        j = w.length();
        table = new Block[i][j];
        up = "u";
        left = "l";
        dia = "d";
        upLeft = "ul";
        upDia = "ud";
        leftDia = "dl";
        udl = "udl";
    }




    public int getMaxWeight(int ii, int jj, Block[][] score) {
        table = score;
        int best = this.scoreDiagonal(ii, jj, score);
        if (scoreAbove(ii, jj, score) > best) {
            best = scoreAbove(ii, jj, score);
        }
        if (scoreLeft(ii, jj, score) > best) {
            best = scoreLeft(ii, jj, score);
        }
        return best;
    }


    public String getBestDirection(int g, int h, Block[][] score) {
        table = score;
        // System.out.println("INSIDE SF: (" + g + "," + h +")");
        int above = scoreAbove(g, h, score);
        int diagonal = scoreDiagonal(g, h, score);
        int lefty = scoreLeft(g, h, score);
        int best = getMaxWeight(g, h, score);

        if (above == diagonal && lefty == diagonal) {
            return udl;
        }
        if (best == above && above == lefty) {
            return upLeft;
        }
        if (lefty == diagonal && best == lefty) {
            return leftDia;
        }
        if (above == diagonal && above == best) {
            return upDia;
        }
        if (above == best) {
            return up;
        }
        if (lefty == best) {
            return left;
        }
        return dia;
    }


    public boolean isMatch(int ab, int bc) {

        return (v.charAt(ab-1) == w.charAt(bc-1));

    }
    
    


    public int scoreAbove(int cool, int bad, Block[][] score) {

        table = score;
        
        // System.out.println("weight: " + table[1][1].getWeight());

        return table[cool - 1][bad].getWeight() + gap;
 
    }


    public int scoreLeft(int df, int fd, Block[][] score) {

        table = score;
        return table[df][fd - 1].getWeight() + gap;
    }


    public int scoreDiagonal(int nc, int ce, Block[][] score) {

        table = score;
        if (this.isMatch(nc, ce)) {
            return table[nc - 1][ce - 1].getWeight() + match;
        }
        return table[nc - 1][ce - 1].getWeight() + mismatch;
    }


    public void printTable(Block[][] score) {
        table = score;
        System.out.println("Table with letters for description:");
        System.out.println(
            "* = no direction, u = up arrow, l = left arrow, d = diagonal arrow");
        StringBuilder startBuild = new StringBuilder();
        startBuild.append("    |");
        StringBuilder newCol = new StringBuilder();
        StringBuilder jLine = new StringBuilder();
        jLine.append("    |      |");
        newCol.append("_____");
        for (int x = 0; x <= j; x++) {
            startBuild.append(x + "     |");
            
            newCol.append("_______");
            if (x > 0) {
                jLine.append(w.charAt(x-1) + "     |"); 
                
            }
            
            
        }
        
        System.out.println(startBuild.toString());
        System.out.println(jLine.toString());
        System.out.println(newCol.toString());
        
        StringBuilder build = new StringBuilder();
        for (int colPrint = 0; colPrint <= i; colPrint++) {
            
            build.append(colPrint + " ");
            if (colPrint > 0) {
                build.append(v.charAt(colPrint-1) + " |");
            }
            else {
                build.append("  |");
            }
            for (int rowPrint = 0; rowPrint <= j; rowPrint++) {
                
                build.append(table[colPrint][rowPrint].printBlock());
                build.append("|");
            }
            build.append("\n" + newCol.toString() + "\n");
            
        }
        System.out.println(build.toString());
        
        //String complete = build.toString();
        
       // System.out.println(complete);
        

    }



    
    /**
     * return the score
     */
    public int sum(String bestV, String bestW) {
        int totalScore = 0;
        for (int y = 0; y < bestV.length(); y++) {
            // If the characters are the same
            if (bestV.charAt(y) == bestW.charAt(y)) {
                totalScore = totalScore + match; 
            }
            else if (bestV.charAt(y) == '-' || bestW.charAt(y) == '-'){
                totalScore = totalScore + gap;
            }
            else {
                totalScore = totalScore + mismatch;
            }
        }
        return totalScore;
    }
    
  
}
