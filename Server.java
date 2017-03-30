import java.util.*;

class Server implements IServer {

    // singleton pattern: 
    public static Server server = new Server(); 
    private Server() {}

    // ------------------------------------------------------------

    private Vector /* SPlayer */ all_players = new Vector(); 

    private int current = 0;
    private int how_many = 0;

    private Die d = new Die(); 


    // add a player to the game 
    public void register(IPlayer p) {
	how_many += 1;
	all_players.add(new SPlayer(p));
    }

    // play the game until all players are done, announce winner 
    public String play() {

	while (a_player_going()) {
	    for(int i = 0; i < how_many; i++) {
		SPlayer p = (SPlayer)all_players.elementAt(i); 
		if (!p.done)
		    p.turn(new Turn(p,d)); 
	    }
	}

	return winner(); 
    }

    // ------------------------------------------------------------

    // or map over the done state of all players
    private boolean a_player_going() {
	for(int i = 0; i < how_many; i++) 
	    if (!((SPlayer)all_players.elementAt(i)).done) {
		// System.out.println("still going " + i);
		return true; 
	    }
	return false; 
    }

    private static String newline = "\n"; 

    // compose the announcement of winners
    // effect: inform winners
    private String winner() {
	int max = max_score(); 
	String announcement = ""; 
	
	for(int i = 0; i < how_many; i++) {
	    SPlayer s = (SPlayer)all_players.elementAt(i);
	    if (max == s.sum) {
		s.inform("You're a winner.");
		announcement = 
		    announcement +
		    newline + 
		    s.name() + 
		    " is a winner " +
		    " with a score of " + max;
	    }
	}

	return announcement; 
    }

    // compute the maximum score for all players
    private int max_score() {
	int max = 0; 
	
	for(int i = 0; i < how_many; i++) {
	    int s = ((SPlayer)all_players.elementAt(i)).sum;
	    if (s > max) 
		max = s; 
	}

	return max;
    }

    // ------------------------------------------------------------------
    // Examples: 

    static MPlayer m1;
    static MPlayer m2;

    static public void createExamples() {
	if (m1 == null) {
	    m1 = new MPlayer("test1"); 
	    m2 = new MPlayer("test2"); 
	}
    }

    // ------------------------------------------------------------------
    // Tests: 

    public static void main(String argv[]) {
	createExamples(); 

	m1.registerDisplay(TestIDisplay.testIDisplay); 
	m2.registerDisplay(TestIDisplay.testIDisplay); 
	
	server.register(m1); 
	server.register(m2); 
	Tester.check(2 == server.how_many,"register 1"); 
	Tester.check(server.a_player_going(),"register 1"); 

	boolean done0 = test_turn(server.all_players.elementAt(0));
	boolean done1 = test_turn(server.all_players.elementAt(1));
	Tester.check(!done0 && !done1,"one turn");
	Tester.check(server.a_player_going(),"a player going");

	while (true) {
	    done0 = test_turn(server.all_players.elementAt(0));
	    done1 = test_turn(server.all_players.elementAt(1));
	    if (done0 && done1) 
		break;
	}

	Tester.check(!server.a_player_going(),"a player going, done");
	
	int score0 = ((SPlayer)server.all_players.elementAt(0)).sum;
	int score1 = ((SPlayer)server.all_players.elementAt(1)).sum;
	
	String w = server.winner(); 

	if (score0 > score1) {
	    Tester.check(server.max_score() == score0,"max score 1"); 
	    Tester.check(w.charAt(5) == '1',"winner 1");
	}
	else if (score1 > score0) {
	    Tester.check(server.max_score() == score1,"max score 2"); 
	    Tester.check(w.charAt(5) == '2',"winner 2");
	}

    }

    private static boolean test_turn(Object s) {
	SPlayer sp = (SPlayer)s;

	return sp.turn(new Turn(sp,server.d)); 
    }

}
