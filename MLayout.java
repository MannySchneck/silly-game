import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

// setting up the layout of the Machine View 
abstract class MLayout extends Layout {
    MLayout(String name) {
	super(1,name);
	frame.setSize(500,80);
	frame.pack();
    }
}
