package model;

import java.util.ArrayList;

public class YoungIncome extends IncomeManage implements DiskonRent {
    Boneka boneka = new Boneka();
    UserManage userManage = new UserManage();
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

        System.out.println("Total Harga     : Rp. " + income);
        System.out.println("Setelah Diskon  : Rp. " + hasil);

        Income data = new Income(firstname, lastname, income, potongan, hasil);

        //untuk mengecek apakah saldo user cukup
        cekSaldoCukup(firstname,lastname);

        if(terpenuhi){

            //mengurangi saldo
            userManage.minusBalance(firstname, lastname, hasil);

            try{
                //membuat dalam server
                rentIncomeDaoModel.create(data);
            }

            catch (Exception e){
                System.out.println("maaf melakukan pinjaman gagal buku baru gagal");
            }
        }

        else{
            System.out.println("maaf saldo anda kurang");
        }


    }

    @Override
    public double diskonBuku(double income, int amount) {
        System.out.println("jumlah " + amount);

        if(amount >= 5){
            potongan = income * 0.2;
            hasil = income - potongan;
            System.out.println("Potongan        : Rp. " + potongan);

            System.out.println("-----------");

            //mendapatkan boneka
            System.out.println("Selamat Anda Mendapatkan Hadiah :");
            boneka.infoProduk();

            System.out.println("-----------");
        }

        else if(amount > 2 ){
            potongan = income * 0.1;
            hasil = income - potongan;
            System.out.println("Potongan        : Rp. " + potongan);
        }

        else{
            potongan = 0;
            System.out.println("Potongan        : Rp. " + potongan);
            hasil = income;
        }

        return hasil;
    }

    @Override
    public boolean isTerpenuhi() {
        return super.isTerpenuhi();
    }
}
