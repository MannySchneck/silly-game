// the turn that is handed to player so that it can play
class Turn implements ITurn {

    SPlayer player; 
    Die d; 

    Turn(SPlayer player, Die d) {
	this.player = player; 
	this.d = d;
    }

    // ------------------------------------------------------------------

    private boolean done = false; 
    // check whether the player already performed an action 
    // effect: set done
    private void okayP() {
	if (done)
	    player.cheat(); 
	done = true; 
    }

    public int roll() {
	okayP(); 

	int result = d.throw_die(); 
	player.record(result); 
	return result;
    }

    public void skip() {
	okayP();
    }

    // ------------------------------------------------------------------
    // Examples: 

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
	SPlayer s1 = t1.player;
	m1.registerDisplay(TestIDisplay.testIDisplay); 

	int r1 = t1.roll(); 
	Tester.check(1 <= r1 && r1 <= 6,"roll 1"); 
	Tester.check(r1 == s1.sum,"roll 2"); 
    }
}
