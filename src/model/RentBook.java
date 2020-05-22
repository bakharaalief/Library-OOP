package model;

import daomodel.UserBookDaoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class RentBook {
    private ArrayList<UserBook> listRent;
    private ArrayList<UserBook> listRentSementara;
    private int amountBookSementara;
    private UserManage userManage;
    private BookStock bookStock;
    private UserBookDaoModel userBookDaoModel;
    private UserBook userBookData;
    private AdultIncome adultIncome;
    private YoungIncome youngIncome;
    private boolean ditemukan;
    private boolean bolehPinjam;
    private User userData;
    private Book bookData;
    private Date date;
    private Calendar c;
    private boolean userDitemukan;
    private boolean bookDitemukan;

    public RentBook(){
        listRentSementara = new ArrayList<>();
        userManage = new UserManage();
        bookStock = new BookStock();
        userBookDaoModel = new UserBookDaoModel();
        date = new Date();
        adultIncome = new AdultIncome();
        youngIncome = new YoungIncome();
        c = Calendar.getInstance();

        amountBookSementara = 0;
        userDitemukan = false;
        bookDitemukan = false;
        bolehPinjam = false;
    }

    //check buku di server
    public void bookRentCheck(String firstnameInput, String lastnameInput, String titleInput, String dateString){
        //merefresh data di database
        listRent = userBookDaoModel.getAll();

        //looping untuk mencari user
        for(int i = 0; i < listRent.size(); i++){
            UserBook data = listRent.get(i);
            String firstname = data.getFirstName().toLowerCase();
            String lastname = data.getLastName().toLowerCase();
            String title = data.getTitle().toLowerCase();
            Date date = data.getRent_start();
            Date tanggalAwal = null;
            java.sql.Date tanggalConvert;

            //untuk mengubah date string ke date
            try {
                tanggalAwal = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            //date di ubah ke sql date
            tanggalConvert = new java.sql.Date(tanggalAwal.getTime());

            //ketika title ditemukan
            if( title.equals(titleInput.toLowerCase()) && firstname.equals(firstnameInput.toLowerCase()) && lastname.equals(lastnameInput.toLowerCase()) && tanggalAwal.equals(tanggalConvert)){
                ditemukan = true;
                userBookData = data;
            }

        }
    }

    //meminjam buku
    public void rentBook(String firstnameInput, String lastnameInput, String titleInput, int amountInput, int rent_days){
        //cek apakah user ada
        userManage.userCheck(firstnameInput, lastnameInput);
        userDitemukan = userManage.getUserDitemukan();

        //cek apakah buku ada
        bookStock.bookCheck(titleInput);
        bookDitemukan = bookStock.getBookDitemukan();

        //ketika kedua data ditemukan
        if(userDitemukan && bookDitemukan){

            //mengambil data user dan book
            userData = userManage.getUserData();
            bookData = bookStock.getBookData();

            String firstname = userData.getFirstname();
            String lastname = userData.getLastname();
            String title = bookData.getTitle();
            int amountStockBook = bookData.getAmount();
            int amount = amountInput;
            boolean returned = false;

            //menggunakan tanggal sekarang
            c.setTime(date); // use now date.


            //hari awal peminjaman
            java.sql.Date startRent = new  java.sql.Date(c.getTime().getTime()); //mengubah date util ke sql

            //hari batas pengembalian
            //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //untuk format
            c.add(Calendar.DATE, rent_days); //menambahkan hari
            java.sql.Date endRent = new  java.sql.Date(c.getTime().getTime()); //mengubah date util ke sql



            //jika jumlah stock ada untuk dipinjam
            if(amountStockBook >= amount){

                //membuat data untuk serber
                UserBook data = new UserBook(firstname, lastname, title, amount, startRent, endRent, returned);
                listRentSementara.add(data);

                System.out.println("Buku dimasukkan dalam list");

            }
            else{
                System.out.println("Maaf tidak bisa melakukan peminjaman karena stok tidak mencukupi");
            }

        }

        //ketika user tidak ditemukan
        else if(!userDitemukan){
            System.out.println("Maaf silahkan anda mendaftar menjadi anggota");
        }

        //ketika buku tidak ditemukan
        else if(!bookDitemukan){
            System.out.println("Maaf buku yang ada cari tidak ada");
        }

        else{
            System.out.println("Maaf data anda salah");
        }
    }

    //list rent check
    public void listRentCheck(){
        //jumlah buku awal
        amountBookSementara = 0;

        //untuk menghitung buku yang dipinjam
        for(int i = 0; i < listRentSementara.size(); i++){
            amountBookSementara += listRentSementara.get(i).getAmount();
        }

        //ketika buku >= 2 boleh pinjam
        if(amountBookSementara >= 2){
            bolehPinjam = true;
        }

        //ketika buku kurang
        else{
            bolehPinjam = false;
        }
    }

    //ketika sudah selesai melakukan data
    public void rentBookSelesai(){
        boolean terpenuhi;

        //cek buku sudah lebih dari 2
        listRentCheck();

        //ketika buku sudah sama dengan 2 atau lebih
        if(bolehPinjam){

            //ketika umur 18 tahun ke atas
            if (userData.getAge() >= 18) {
                adultIncome.rentTotal(listRentSementara);
                terpenuhi = adultIncome.isTerpenuhi();
            }

            //selain itu
            else {
                youngIncome.rentTotal(listRentSementara);
                terpenuhi = youngIncome.isTerpenuhi();
            }

            if(terpenuhi){
                for ( int i = 0; i < listRentSementara.size(); i++) {
                    UserBook data = listRentSementara.get(i);
                    String titleInput = data.getTitle();
                    int amountInput = data.getAmount();

                    try {
                        //membuat di dalam server
                        userBookDaoModel.create(data);

                        //mengurangi stock buku
                        bookStock.minusStockBook(titleInput, amountInput);

                    } catch (Exception e) {
                        System.out.println("maaf melakukan pinjaman gagal buku baru gagal");
                    }
                }
            }

            else {
                //System.out.println("maaf saldo anda kurang");
            }

            listRentSementara.clear();
            bolehPinjam = false;
        }

        //buku masih kurang
        else{
            System.out.println("Maaf buku anda kurang untuk melakukan peminjaman");
        }
    }

    public void returnBook(String firstnameInput, String lastnameInput, String titleInput, String dateInput){
        //ditemukan
        bookRentCheck(firstnameInput, lastnameInput, titleInput, dateInput);

        //ditemukan
        if(ditemukan){

            //ketika user sudah mengembalikan buku
            if(userBookData.isReturned()){
                System.out.println("maaf buku sudah dikembalikan");
            }

            //ketika user belum mengembalikan buku
            else{
                int id = userBookData.getId();
                c.setTime(date);
                java.sql.Date return_date = new java.sql.Date(c.getTime().getTime());
                boolean returned = true;

                //data
                UserBook data = new UserBook(id, return_date, returned);

                try {
                    userBookDaoModel.returnedBook(data);

                    //menambah stock buku lagi
                    bookStock.plusStockBook(titleInput, userBookData.getAmount());

                    ditemukan = false;
                }

                catch (Exception e) {
                    System.out.println("Maaf pengembalian buku gagal");
                }
            }
        }

        //tidak
        else{
            System.out.println("Maaf pengembalian gagal karena data tidak ditemukan");
        }
    }

    //mengambil jumlah buku yang dipinjam
    public int getAmountBookSementara(){
        listRentCheck();
        return amountBookSementara;
    }
}
