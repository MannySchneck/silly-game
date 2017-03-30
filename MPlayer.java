class MPlayer extends Player {

    MPlayer(String name) { super(name); }
    
    // ------------------------------------------------------------------

    private IDisplay io;

    public void registerDisplay(IDisplay d) {
	this.io = d; 
    }

    public void inform(String msg) {
	io.msgDisplay(msg); 
    }

    public boolean turn(Turn t) {
	io.msgDisplay("It's your turn.");

	if (sum > 19) 
	    t.skip(); 
	else if (sum < 19) 
	    sum += t.roll(); 

	io.sumDisplay(sum);

	if (sum <= 21) 
	    io.msgDisplay("Turn's up"); 
	else
	    io.msgDisplay("Oops, lost this one."); 

	if (sum < 18) 
	    return false; 
	else 
	    return true;
    }

    // ------------------------------------------------------------------
    // Examples: 

    static MPlayer m1;
    static SPlayer s1;
    static Turn t1; 
    static Turn t2; 

    public static void createExamples() {
	if (m1 == null) {
	    m1 = new MPlayer("test1");    
	    s1 = new SPlayer(m1);         
	    t1 = new Turn(s1, new Die()); 
	    t2 = new Turn(s1, new Die());
	}
    }

   
    // ------------------------------------------------------------------
    // Tests: 

    public static void main(String argv[]) {
	m1.registerDisplay(TestIDisplay.testIDisplay); 

	m1.inform("welcome to player 1");
	
	boolean done = m1.turn(t1);
	Tester.check(!done,"turn 1");
	
	m1.sum = 20; 
	done = m1.turn(t2); 
	Tester.check(done,"turn 2");

	done = m1.turn(t2);
	Tester.check(s1.done,"cheat 3"); 
	Tester.check(s1.sum == 0,"cheat 4"); 
    }
}
