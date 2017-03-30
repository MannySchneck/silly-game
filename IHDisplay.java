interface IHDisplay extends IDisplay {

    // register a listener for button events, 
    // wait until one of the buttons has been clicked
    void listen(IListener ll);
}
