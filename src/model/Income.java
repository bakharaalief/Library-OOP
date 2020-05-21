package model;

import java.sql.Date;

public class Income {
    private int id;
    private String firstname;
    private String lastname;
    private double income;
    private double discount;
    private double final_income;
    private Date created;

    public Income(int id, String firstname, String lastname, double income, double discount, double final_income, Date created) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.income = income;
        this.discount = discount;
        this.final_income = final_income;
        this.created = created;
    }

    public Income(String firstname, String lastname, double income, double discount, double final_income) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.income = income;
        this.discount = discount;
        this.final_income = final_income;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinal_income() {
        return final_income;
    }

    public void setFinal_income(double final_income) {
        this.final_income = final_income;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
