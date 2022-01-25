import student.TestCase;

public class MainTest extends TestCase {
    private String global;
    private String v;
    private String w;
    private String local;
    private DynamicPro dpl;
    private DynamicPro dpg;
    private int[] scoringMatrix;
    
    public void setUp() {
        v = "TACGGGTAT";
        w = "GGACGTACG";
    
        dpl = new DynamicPro();
        dpg = new DynamicPro();
        global = "global";
        local = "local";
        scoringMatrix = new int[]{1,-1,-1};
        
        
    }
    
    public void testPrintGlobal() {
        dpg.main(v,w,scoringMatrix, global);
        
    }
    public void testPrintLocal() {
        dpl.main(v,w,scoringMatrix,local);
    }
    

}
