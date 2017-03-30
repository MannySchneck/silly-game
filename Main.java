// java Main [human-name]

// to create (up to) three players and an optional human player
// start the game, when the game is over print the winner announcement

class Main {
    public static void main(String argv[]) {
	Server s = Server.server; 
	String p[] = new String [] { "matthias", "matthew", "shriram"};
	
	// need to insert human player into chain at random place 

	if (argv.length >= 1) {
	    IHDisplay io = new HView(argv[0]); 
	    IPlayer human = new HPlayer(argv[0]); 
	    Controller c = new Controller(human,io); 
	    s.register(human);
	}

	for(int i = 0; i < p.length; i++) {
	    IDisplay v = new MView(p[i]);
	    IPlayer pl = new MPlayer(p[i]); 
	    Controller c = new Controller(pl,v);
	    s.register(pl);
	}
	
	System.out.println(s.play());
    }
}
