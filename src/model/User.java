package model;


import java.sql.Date;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private int age;
    private double balance;
    private Date created;

    //with id and date ( untuk get info biodata )
    public User(int id, String firstname, String lastname, int age, double balance, Date created) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.balance = balance;
        this.created = created;
    }

    //not with id and date ( untuk create user )
    public User(String firstname, String lastname, int age, double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.balance = balance;
    }

    //not date ( untuk update data profile )
    public User(int id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
