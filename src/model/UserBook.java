package model;

import java.sql.Date;

public class UserBook {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private int amount;
    private Date rent_start;
    private Date rent_end;
    private Date return_date;
    private boolean returned;

    public UserBook(int id, String firstName, String lastName, String title, int amount, Date rent_start, Date rent_end, Date return_date, boolean returned) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.amount = amount;
        this.rent_start = rent_start;
        this.rent_end = rent_end;
        this.return_date = return_date;
        this.returned = returned;
    }

    //untuk create data
    public UserBook(String firstName, String lastName, String title, int amount, Date rent_start, Date rent_end, boolean returned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.amount = amount;
        this.rent_start = rent_start;
        this.rent_end = rent_end;
        this.return_date = return_date;
        this.returned = returned;
    }

    //untuk pengembalian buku
    public UserBook(int id, Date return_date, boolean returned){
        this.id = id;
        this.return_date = return_date;
        this.returned = returned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getRent_start() {
        return rent_start;
    }

    public void setRent_start(Date rent_start) {
        this.rent_start = rent_start;
    }

    public Date getRent_end() {
        return rent_end;
    }

    public void setRent_end(Date rent_end) {
        this.rent_end = rent_end;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
