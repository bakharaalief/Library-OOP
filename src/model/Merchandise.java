package model;

abstract class Merchandise {
    private String name;
    private double price;

    public Merchandise(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void infoProduk(){
        System.out.println("Nama Barang : " + name);
    };

    abstract void usability();
}
