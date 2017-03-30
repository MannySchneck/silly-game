// a simplistic implementation of the IHDisplay interface 
class TestIHDisplay extends TestIDisplay implements IHDisplay {

    TestIHDisplay() { super(); }

    public void listen(IListener ll) {
	int i = (int)Math.floor(3 * Math.random()); 
	if (0 == i) 
	    i = 3; 
	// now 1 <= i <= 3

	if (1 == i)
	    ll.roll();
	else if (2 == i) 
	    ll.skip(); 
	else // (3 == i)
	    ll.done(); 
    }

    // a useful instance 
    static public IHDisplay testIDisplay = new TestIHDisplay();
}

