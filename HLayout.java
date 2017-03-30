// the Views: determine ordering of button and label with boolean 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

// setting up the layout of the View 
abstract class HLayout extends Layout {
    JButton rollb = new JButton("roll"); 
    JButton doneb = new JButton("done"); 
    JButton skipb = new JButton("skip"); 

    HLayout(String name) {
	super(2,name);

	pane.add(rollb);
	pane.add(skipb);
	pane.add(doneb); 

	frame.setSize(500,100);
	frame.pack(); 
    }

    // ------------------------------------------------------------------

    // set the enabling of all buttons 
    protected void setEnabledAll(boolean b) {
	rollb.setEnabled(b);
	doneb.setEnabled(b);
	skipb.setEnabled(b);
    }

    // for testing, coordinates for pixels on the buttons
    protected static int ROLLX = 100; 
    protected static int ROLLY = 100; 

    protected static int SKIPX = 200; 
    protected static int SKIPY = ROLLY;
	
    protected static int DONEX = 350; 
    protected static int DONEY = ROLLY;
}
