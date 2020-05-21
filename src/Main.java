import daomodel.UserDaoModel;
import model.*;

import java.sql.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        /*buat objek*/
        UserManage userManage = new UserManage();
        BookStock bookStock = new BookStock();
        RentBook rentBook = new RentBook();
        AdultIncome adultIncome = new AdultIncome();
        IncomeManage incomeManage = new IncomeManage();


        System.out.println("Selamat Datang di Perpustakaan ");

//        /*buku buku baru*/
//        bookStock.importNewBook("angker", "angker banget uhh", "horor", "deres sedu", 2015,20000,20);
//        bookStock.importNewBook("ayam", "ayam itu keren banget", "hewan", "agus otot", 2020,20000,20);
//        bookStock.importNewBook("ayam goreng", "ayammmmm", "Hewan", "jono", 2010, 20000, 20);

//        /*cek buku*/
//        bookStock.bookCheck("ayam");

//        /*delete book*/
//        bookStock.deleteBook("ayam");

        /*update book*/
//        bookStock.updateInfoBook("kecap");

        /*update stock book total*/
//        bookStock.plusStockBook("angker", 6);
//        bookStock.minusStockBook("angker", 6);

//        /*cek semua buku digudang*/
//        bookStock.listBook();

        /*buat user*/;
//        userManage.createUser("bambang","agus",12);
//        userManage.createUser("Bambang", "Dodo",10);
//        userManage.createUser("jono","tampan", 30);

//        /*deleteUser*/
//        userManage.deleteUser("jono", "tampan");
//        userManage.deleteUser("Bambang","Dodo");

        /*update user*/
//        userManage.updateUser("Bambang","Dodo");
//        userManage.updateUser("jono","tampan");

        /*cari buku*/
//        bookStock.searchBookGenre();
//        bookStock.searchBookTitle("wadaw");

        /*minjem buku*/
//        rentBook.rentBook("agus", "dodo","wadaw", 1, 2);
//        rentBook.rentBook("agus","dodo","angker",2,2);
//        rentBook.rentBookSelesai();

//        rentBook.rentBook("bambang","agus","wadaw",3,2);
//        rentBook.rentBookSelesai();

        //mencari total pendapatan
//        incomeManage.totalPendapatan();


        /*balikin buku*/
        //rentBook.returnBook("agus","dodo","wadaw","2020-05-20");

        /*rent price*/
        //adultIncome.rentPrice("wadaw", "wadaw", "wadaw", 10000,10, 2);
    }
}
