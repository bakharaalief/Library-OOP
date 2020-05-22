package model;

import daomodel.UserDaoModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManage {
    private ArrayList<User> listUser;
    private User userData;
    private boolean ditemukan;
    private UserDaoModel userDaoModel;
    private Scanner input = new Scanner(System.in);

    public UserManage(){
        userDaoModel = new UserDaoModel();
        ditemukan = false;
    }

    //mengecek user ada atau tidak di server
    public void userCheck(String firstnameInput, String lastnameInput){
        //merefresh data di database
        listUser = userDaoModel.getAll();

        //looping untuk mencari user
        for(int i = 0; i < listUser.size(); i++){
            User data = listUser.get(i);
            String firstname = data.getFirstname().toLowerCase();
            String lastname = data.getLastname().toLowerCase();

            //ketika user ditemukan
            if( firstname.equals(firstnameInput.toLowerCase()) && lastname.equals(lastnameInput.toLowerCase())){
                ditemukan = true;
                userData = data;
                break;
            }

            else{
                ditemukan = false;
            }
        }
    }

    //create user
    public void createUser ( String firstnameInput, String lastnameInput, int ageInput){
        //melakukan cek dulu
        userCheck(firstnameInput, lastnameInput);

        //jika user sudah ada
        if(ditemukan){
            System.out.println("maaf nama anda sudah tersedia");
        }

        //belum ada
        else{
            String firstname = firstnameInput;
            String lastname = lastnameInput;
            int age = ageInput;
            double balance = 5000;

            //data yang dibuat untuk input ke create
            User data = new User(firstname, lastname, age, balance);

            try{
                //membuat user di server
                userDaoModel.create(data);
            }

            catch (Exception e){
                System.out.println("maaf membuat akun gagal");
            }
        }
    }

    //update user
    public void updateUser ( String firstnameInput, String lastnameInput){
        //melakukan cek dulu
        userCheck(firstnameInput, lastnameInput);

        //jika ditemukan
        if(ditemukan){
            int id = userData.getId();
            System.out.print("Nama depan baru : ");
            String newFirstname = input.next();
            System.out.print("Nama belakang baru : ");
            String newLastname = input.next();
            System.out.print("Umur baru : ");
            int newAge = input.nextInt();

            //data baru untuk diset
            User dataBaru = new User(id,newFirstname,newLastname,newAge);

            try{
                userDaoModel.update(dataBaru);
            }

            catch (Exception e){
                System.out.println("maaf perubahan tidak dapat dilakukan");
            }

        }
        else{
            System.out.println("Maaf perubahan tidak dapat dilakukan karena data tidak ditemukan");
        }

    }

    //menambah balance
    public void addBalance(String firstnameInput, String lastnameInput, double balance){
        //cek terlebih dahulu
        userCheck(firstnameInput, lastnameInput);

        int id = userData.getId();
        double saldoAwal = userData.getBalance();
        double saldoBaru = saldoAwal + balance;

        User data = new User(id, saldoBaru);

        //jika data ada
        if(ditemukan) {
            try {
                userDaoModel.updateBalance(data);
                ditemukan = false;
            }

            catch (Exception e) {
                System.out.println("Maaf saldo tidak dapat ditambah");
            }
        }

        //jika nggak
        else{
            System.out.println("Maaf saldo tidak dapat ditambah karena data tidak ditemukan");
        }
    }

    //mengurangi balance
    public void minusBalance(String firstnameInput, String lastnameInput, double balance){
        //cek terlebih dahulu
        userCheck(firstnameInput, lastnameInput);

        int id = userData.getId();
        double saldoAwal = userData.getBalance();
        double saldoBaru = saldoAwal - balance;

        User data = new User(id, saldoBaru);

        //jika data ada
        if(ditemukan) {
            try {
                userDaoModel.updateBalance(data);
                ditemukan = false;
            }

            catch (Exception e) {
                System.out.println("Maaf saldo tidak dapat ditambah");
            }
        }

        //jika nggak
        else{
            System.out.println("Maaf saldo tidak dapat ditambah karena data tidak ditemukan");
        }
    }

    //delete user
    public void deleteUser( String firstnameInput, String lastnameInput ){
        //mencek dulu
        userCheck(firstnameInput, lastnameInput);

        //jika data ada
        if(ditemukan) {
            try {
                userDaoModel.delete(userData);
                ditemukan = false;
            }

            catch (Exception e) {
                System.out.println("Maaf user tidak berhasil dihapus");
            }
        }

        //jika nggak
        else{
            System.out.println("Maaf user tidak berhasil dihapus karena data tidak ditemukan");
        }
    }

    //info user
    public void userInfo( String firstnameInput, String lastnameInput ){
        //melakukan check dulu
        userCheck(firstnameInput, lastnameInput);

        //apabila check berhasil
        if(ditemukan){
            //mengubah tanggal
            String pattern = "E, dd MMM yyyy"; //output ex: sun, 17 may 2020
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(userData.getCreated());

            //bio yang muncul
            System.out.println("Nama Depan      : " + userData.getFirstname());
            System.out.println("Nama Belakang   : " + userData.getLastname());
            System.out.println("Umur            : " + userData.getAge());
            System.out.println("Saldo           : " + userData.getBalance());
            System.out.println("Bergabung       : " + date);
        }

        //check tidak berhasil
        else {
            System.out.println("maaf data user tidak ditemukan");
        }
    }

    //get ditemukan
    public boolean getUserDitemukan(){
        return ditemukan;
    }

    //get user data
    public User getUserData(){
        return userData;
    }
}
