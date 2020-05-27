package model;

public class Boneka extends Merchandise {

    public Boneka(){
        super("Boneka",10000);
    }

    @Override
    void usability() {
        System.out.println("Sebagai Hiasan");
    }
}
