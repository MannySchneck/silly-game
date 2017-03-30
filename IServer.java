interface IServer {
    // register a player with the game 
    void register(IPlayer P); 

    // start playing, compute an announcement about winners
    String play(); 
}
