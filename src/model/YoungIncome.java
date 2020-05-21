package model;

import java.util.ArrayList;

public class YoungIncome extends IncomeManage implements DiskonRent {
    private double hasil;
    double potongan;
    double rentCost;


    @Override
    public double rentPrice(String titleInput, int amountInput, long rentDay){
        percent = 0.05; //harga biaya untuk orang dewasa
        rentCost = super.rentPrice(titleInput, amountInput, rentDay);

        return rentCost;
    }

    @Override
    public void rentTotal(ArrayList<UserBook> listPinjam){
        super.rentTotal(listPinjam);

        diskonBuku(income, totalBanyakBuku);

        System.out.println("total harga : " + income);
        System.out.println("setelah diskon : " + hasil);

        Income data = new Income(firstname, lastname, income, potongan, hasil);

        try{
            //membuat dalam server
            rentIncomeDaoModel.create(data);
        }

        catch (Exception e){
            System.out.println("maaf melakukan pinjaman gagal buku baru gagal");
        }
    }

    @Override
    public double diskonBuku(double income, int amount) {
        if(amount > 2 ){
            potongan = income * 0.05;
            hasil = income - potongan;
            System.out.println("potongan " + potongan);
        }
        else{
            potongan = 0;
            hasil = income;
        }

        return hasil;
    }
}
