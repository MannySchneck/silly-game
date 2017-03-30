import java.awt.*;
import java.awt.event.*;

class Tester {

    static int faults = 0; 

    static void check(boolean t, String s) {
	if (!t) {
	    faults += 1; 
	    System.out.println("*** test failed: " + s + " *** "); 
	}
    }

    public static void main(String argv[]) {
	System.out.println("setting up examples ..."); 
	Turn.createExamples(); 
	SPlayer.createExamples(); 
	MPlayer.createExamples();
	HPlayer.createExamples();

	Server.createExamples();

	MView.createExamples();
	HView.createExamples();

	System.out.println("testing ..."); 
	Die.main(argv);

	Turn.main(argv); 
	SPlayer.main(argv); 
	MPlayer.main(argv);
	HPlayer.main(argv);

	Server.main(argv);

	MView.main(argv);
	HView.main(argv);

	System.out.println("faulty tests: " + faults); 
	System.exit(0);
    }

}
