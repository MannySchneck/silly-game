class Controller {
    IDisplay view; 
    IPlayer model; 

    Controller(IPlayer m, IDisplay v) {
	 view = v; 
	 model = m;
	 m.registerDisplay(v); 
    }
}
