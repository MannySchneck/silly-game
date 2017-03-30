import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

// setting up the layout of the Machine View 
abstract class Layout {
    protected JFrame frame;
    protected JPanel pane;
    protected JLabel sump;
    protected JLabel msgp;

    // ------------------------------------------------------------------

    Layout(int rows, String name) {
	frame = new JFrame("player: " + name);
	pane = new JPanel();

	pane.setLayout(new GridLayout(rows,3));

	msgp  = add_label(pane," space for messages "); 
	sump  = add_label(pane,sums(0)); 
	add_label(pane," " + name);

	frame.addWindowListener
	    (new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
			System.exit(0);
		    }}); 
	
	frame.getContentPane().add(pane);
	frame.pack();
	frame.setVisible(true);
    }
    

    // add a label to the pane 
    private JLabel add_label(JPanel pane, String s) {
	JLabel l = new JLabel(s);

	pane.add(l,SwingConstants.CENTER);
	return l; 
    }

    // convert the current sum into display string
    protected String sums(int s) {
	return " current value: " + s;
    }

    // ------------------------------------------------------------------
    // Test: common test functions for GUIs

    void quit(Robot r) {
	frame.setVisible(false);
	// r.keyPress(KeyEvent.VK_META);
	// r.keyPress(KeyEvent.VK_Q);
    }

    // click on mouse button for r at [x,y]
    public static void press(Robot r, int x, int y) {
	r.mouseMove(x,y); 
	r.mousePress(InputEvent.BUTTON1_MASK);
	r.mouseRelease(InputEvent.BUTTON1_MASK);
    }

}

