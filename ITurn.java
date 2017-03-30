interface ITurn {
    // player rolls the die for this turn, record the result with state 
    int /* 1 .. 6 */ roll();

    // player skips this turn 
    void skip(); 
}
