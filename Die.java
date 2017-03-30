class Die {

    // create a random int between 1 and 6 (incl)
    public int throw_die() {
	int i = (int)Math.floor(6 * Math.random()); 
	if (0 == i) 
	    return 6; 
	else 
	    return i; 
    }

    // ------------------------------------------------------------------
    // TEST

    public static void main(String argv[]) { 
	Die d = new Die(); 
	int test[] = new int[6];
	int index;
	for(int i = 0; i < 120000; i++) {
	    index = d.throw_die() - 1;
	    test[index] += 1;
	}

	boolean dist = true; 
	for(int i = 0; i < 6; i++) 
	    dist = dist && (18500 < test[i] && test[i] < 21500); 
	Tester.check(dist,"die");
	
    }
}
