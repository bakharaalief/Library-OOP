package model;

public class Book {
    private int id;
    private String title;
    private String synopsis;
    private String genre;
    private String writer;
    private int published;
    private double price;
    private int amount;

    //untuk get info buku
    public Book(int id, String title, String synopsis, String genre, String writer, int published, double price, int amount){
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.writer = writer;
        this.published = published;
        this.price = price;
        this.amount = amount;
    }

    //( tanpa id ) untuk import new buku
    public Book(String title, String synopsis, String genre, String writer, int published, double price, int amount){
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.writer = writer;
        this.published = published;
        this.price = price;
        this.amount = amount;
    }

    //( tanpa amount ) untuk update buku
    public Book(int id, String title, String synopsis, String genre, String writer, int published, double price){
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.writer = writer;
        this.published = published;
        this.price = price;
    }

    //untuk stock update
    public Book(int id, int amount){
        this.id = id;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
