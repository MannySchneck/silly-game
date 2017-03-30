// Server internal representation of a player 

class SPlayer {
    protected boolean done = false; 
    protected int sum = 0; /* <= 21 */
    private IPlayer player; 

    SPlayer(IPlayer player) { this.player = player; }

    // ------------------------------------------------------------------

    // allow player to take a turn
    public boolean turn(Turn t) {
	done = player.turn(t); 
	return done;
    }

    // add in result to sum and make sure it's not over 21 
    // pre: !done
    public void record(int /* 1 .. 6 */ result) {
	if (done)
	    sum = 0; 
	else
	    sum += result; 

	if (21 == sum) 
	    done = true; 
	else if (21 < sum) {
	    done = true; 
	    sum = 0;
	};
    }

    // record the player's attempt to cheat 
    public void cheat() {
	done = true; 
	sum = 0; 
	player.inform("You cheated. You're out."); 
    }
    
    public String name() {
	return player.name(); 
    }
    
    public void inform(String s) {
	player.inform(s); 
    }
    
    // ------------------------------------------------------------------
    // Examples: 
    static MPlayer m1;
    static SPlayer s1;

    static public void createExamples() {
	if (m1 == null) {
	    m1 = new MPlayer("machine play for test of splayer");
	    s1 = new SPlayer(m1);
	} 
    }

    // ------------------------------------------------------------------
    // Test
    public static void main(String argv[]) {
	createExamples();

	m1.registerDisplay(TestIDisplay.testIDisplay);

	s1.record(6);
	Tester.check(s1.sum == 6,"record 6"); 

	s1.record(3);
	Tester.check(s1.sum == 9,"record 9");

	s1.record(6);
	s1.record(6);
	Tester.check(s1.done,"done 21"); 

	// calling record in improper context 
	s1.record(3); 
	Tester.check(s1.sum == 0,"done 21"); 

	s1.cheat(); 
	Tester.check(s1.done,"cheat 1"); 
	Tester.check(s1.sum == 0,"cheat 2"); 
    }

}
