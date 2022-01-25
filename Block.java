/**
 * Class represents one block in the table.
 * Holds a direction and a number. 
 * @author camil
 *
 */
public class Block {
    private int weight;
    private String arrow;
    
    
    /**
     * Constructor for each block in the table
     * @param number
     * @param direction
     */
    public Block(int number, String direction) {
        weight = number;
        arrow = direction;
    } 
    
    /**
     * getter method for the weight
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Getter method for the arrow
     */
    public String getArrow() {
        return arrow;
    }
    
    public String printBlock() {
        StringBuilder strBuild = new StringBuilder();
        if (weight == 0 || weight > 0) {
            strBuild.append(" ");
            
        }
        strBuild.append(weight);
        
        strBuild.append(',');
        
        strBuild.append(arrow);
        
        for (int k = arrow.length(); k < 3; k++) {
            strBuild.append(" ");
        }
        
        return strBuild.toString();
    }

}
